package cn.com.liliyun.coach.service.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.com.liliyun.coach.mapper.CoachAreaMapper;
import cn.com.liliyun.coach.mapper.CoachClassinfoMapper;
import cn.com.liliyun.coach.mapper.CoachMapper;
import cn.com.liliyun.coach.mapper.CoachModApplyMapper;
import cn.com.liliyun.coach.mapper.CoachStoreMapper;
import cn.com.liliyun.coach.mapper.HeadCoachMapper;
import cn.com.liliyun.coach.mapper.StuAssignMapper;
import cn.com.liliyun.coach.model.Coach;
import cn.com.liliyun.coach.model.CoachArea;
import cn.com.liliyun.coach.model.CoachClassinfo;
import cn.com.liliyun.coach.model.CoachJob;
import cn.com.liliyun.coach.model.CoachLoadStudentInfo;
import cn.com.liliyun.coach.model.CoachModApply;
import cn.com.liliyun.coach.model.CoachModApplyParam;
import cn.com.liliyun.coach.model.CoachStore;
import cn.com.liliyun.coach.model.CoachStudentDTO;
import cn.com.liliyun.coach.model.CoachTeachType;
import cn.com.liliyun.coach.model.HeadCoach;
import cn.com.liliyun.coach.model.StudentAssign;
import cn.com.liliyun.coach.service.CoachService;
import cn.com.liliyun.coach.service.CoachSettingService;
import cn.com.liliyun.common.model.RequestContext;
import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.common.model.ResultCode;
import cn.com.liliyun.common.model.UploadImage;
import cn.com.liliyun.common.util.ConstantUtil;
import cn.com.liliyun.common.util.HttpConstant;
import cn.com.liliyun.common.util.PageUtil;
import cn.com.liliyun.common.util.StringUtil;
import cn.com.liliyun.flow.model.Flow;
import cn.com.liliyun.flow.model.FlowStep;
import cn.com.liliyun.flow.service.FlowService;
import cn.com.liliyun.log.manager.impl.LogCommonManagerImpl;
import cn.com.liliyun.log.model.LogCommon;
import cn.com.liliyun.log.util.LogCommonUtil;
import cn.com.liliyun.student.model.Student;
import cn.com.liliyun.student.service.StudentService;
import cn.com.liliyun.trainorg.model.Area;
import cn.com.liliyun.trainorg.model.Store;
import cn.com.liliyun.trainorg.service.AreaService;
import cn.com.liliyun.trainorg.service.StoreService;
import cn.com.liliyun.user.model.User;
import cn.com.liliyun.user.service.UserService;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

@SuppressWarnings("rawtypes")
@Service
public class CoachServiceImpl implements CoachService {
	Logger logger = Logger.getLogger(CoachServiceImpl.class);

	@Autowired
	private CoachMapper coachMapper;

	@Autowired
	private CoachStoreMapper coachStoreMapper;

	@Autowired
	private CoachClassinfoMapper coachClassinfoMapper;

	@Autowired
	private CoachAreaMapper coachAreaMapper;

	@Autowired
	private CoachSettingService settingService;

	@Autowired
	private StoreService storeService;

	@Autowired
	private AreaService areaService;
	@Autowired
	private LogCommonManagerImpl logCommonService;

	@Autowired
	private StuAssignMapper stuAssignMapper;

	@Autowired
	private CoachModApplyMapper coachModApplyMapper;
	@Autowired
	private HeadCoachMapper headCoachMapper;
	@Autowired
	private FlowService flowService;
	@Autowired
	private UserService userService;
	@Autowired
	private StudentService studentService;

	@Value("${data.synch}")
	private boolean APP_SYNCH;

	@Override
	public ResultBean addCoach(Coach coach,  Map extendinfo,
			User user) {
		ResultBean r = new ResultBean();
		Coach cac = new Coach();
		cac.setDblink(coach.getDblink());
		cac.setMobile(coach.getMobile());

		List listm = coachMapper.selectByterm(cac);
		if (listm.size() > 0) {
			r.setCode(3);
			r.setMsg("手机号已存在!");
			return r;
		}

		Coach coa = new Coach();
		coa.setDblink(coach.getDblink());
		coa.setDrilicence(coach.getDrilicence());
		List listd = coachMapper.selectByterm(coa);
		if (listd.size() > 0) {
			r.setCode(3);
			r.setMsg("驾驶证号码已存在!");
			return r;
		}

		Coach coac = new Coach();
		coac.setDblink(coach.getDblink());
		coac.setIdcard(coach.getIdcard());
		List listi = coachMapper.selectByterm(coac);
		if (listi.size() > 0) {
			r.setCode(3);
			r.setMsg("身份证号码已存在!");
			return r;
		}
		coach.setCtime(new Date());
		coach.setMtime(new Date());
		coachMapper.insertSelective(coach);

		if (coach.getHeadcoachid() != null && coach.getHeadcoachid() > 0) {
			HeadCoach headCoach = new HeadCoach();
			headCoach.setDblink(user.getDblink());
			headCoach.setCoachid(coach.getHeadcoachid());
			headCoach.setOvercoach(1);
			headCoach.setOvercoachcar(0);

			headCoachMapper.updateHeadCoachData(headCoach);

		}

		if (coach.getHeadcoachflag() != null && coach.getHeadcoachflag() == 1) {// 组长
			addHeadCoach(coach.getCoachid());
		}

		if (extendinfo.containsKey("storeid")) {
			String[] newstores = (String[]) extendinfo.get("storeid");
			updateStore(newstores, coach.getCoachid());
		}
		if (extendinfo.containsKey("classinfoid")) {
			String[] newclassinfoids = (String[]) extendinfo.get("classinfoid");
			updateClassinfo(newclassinfoids, coach.getCoachid());
		}
		if (extendinfo.containsKey("step2areaid")) {
			String[] newstep2areaids = (String[]) extendinfo.get("step2areaid");
			updateStep2Area(newstep2areaids, coach.getCoachid());
		}
		if (extendinfo.containsKey("step3areaid")) {
			String[] newstep3areaids = (String[]) extendinfo.get("step3areaid");
			updateStep3Area(newstep3areaids, coach.getCoachid());
		}
		// 插入图片
		int id = coach.getCoachid();
		String array = coach.getImgeArray();
		if (!StringUtil.isNullString(array)) {
			UploadImage ui = new UploadImage();
			ui.setBusinessId(id);
			ui.setModule("coach");
			String[] images = array.split(",");
			for (String key : images) {
				ui.setDblink(coach.getDblink());
				ui.setImgeKey(key);
				coachMapper.saveImage(ui);
			}
		}

		// if (APP_SYNCH) {
		// /************ APP数据同步 ************/
		// Map <String,Object> params = new HashMap<String, Object>();
		// if (coach.getSchoolid() == null) {
		// coach.setSchoolid(0);
		// }
		//
		// params.put("schoolId" , coach.getSchoolid());
		// params.put("schoolName" , coach.getSchoolname());
		// params.put("name" , coach.getName());
		// params.put("idNumber", coach.getIdcard());
		// params.put("sex", coach.getSex());
		// params.put("phoneNum", coach.getMobile());
		// params.put("drLicence", coach.getDrilicence());
		//
		// String result = "";
		//
		// JSONObject resultObj = JSONObject.parseObject(result);
		// if (HttpConstant.SUCCESS_CODE == resultObj.getIntValue("code")) {
		// Coach c = new Coach();
		// c.setDblink(coach.getDblink());
		// c.setCoachid(coach.getCoachid());
		// c.setXid(resultObj.getInteger("coachid"));
		// coachMapper.updateByPrimaryKeySelective(c);
		// }
		// }
		return r;
	}

	private void addHeadCoach(int coachid) {
		HeadCoach headCoach = new HeadCoach();
		//headCoach.setDblink(user.getDblink());
		headCoach.setCoachid(coachid);
		headCoach.setOvercoach(0);
		headCoach.setOvercoachcar(0);
		headCoachMapper.addHeadCoach(headCoach);
	}

	private void updateStore(String[] newstores, Integer coachid) {
		CoachStore param = new CoachStore();
		param.setCoachid(coachid);
		//param.setDblink(user.getDblink());
		List<CoachStore> all = coachStoreMapper.list(param);

		List<CoachStore> addtypes = new ArrayList();
		List<CoachStore> deltypes = new ArrayList();
		for (String ntype : newstores) {
			boolean flag = false;
			for (CoachStore type : all) {
				if (Integer.parseInt(ntype) == type.getStoreid()) {
					flag = true;
					break;
				}
			}
			if (!flag) {
				CoachStore n = new CoachStore();
				//n.setDblink(user.getDblink());
				n.setCoachid(coachid);
				n.setStoreid(Integer.parseInt(ntype));
				addtypes.add(n);
			}
		}

		for (CoachStore type : all) {
			boolean flag = false;
			for (String ntype : newstores) {
				if (Integer.parseInt(ntype) == type.getStoreid()) {
					flag = true;
					break;
				}
			}
			if (!flag) {
				deltypes.add(type);
			}
		}

		if (addtypes.size() > 0) {
			Map params = new HashMap();
			params.put("list", addtypes);
			//params.put("dblink", user.getDblink());
			coachStoreMapper.batchAdd(params);
		}
		if (deltypes.size() > 0) {
			Map params = new HashMap();
			params.put("list", deltypes);
			//params.put("dblink", user.getDblink());
			coachStoreMapper.batchDel(params);
		}
	}

	private void updateStep2Area(String[] newstores, Integer coachid) {
		CoachArea param = new CoachArea();
		//param.setDblink(user.getDblink());
		param.setCoachid(coachid);
		param.setStep(2);
		updateStepArea(newstores, coachid,param);
	}

	private void updateStep3Area(String[] newstores, Integer coachid) {
		CoachArea param = new CoachArea();
		//param.setDblink(user.getDblink());
		param.setCoachid(coachid);
		param.setStep(3);
		updateStepArea(newstores, coachid,  param);
	}

	private void updateStepArea(String[] newareas, Integer coachid,
			CoachArea param) {
		List<CoachArea> all = coachAreaMapper.list(param);

		List<CoachArea> addtypes = new ArrayList();
		List<CoachArea> deltypes = new ArrayList();
		for (String ntype : newareas) {
			boolean flag = false;
			for (CoachArea type : all) {
				if (Integer.parseInt(ntype) == type.getAreaid()) {
					flag = true;
					break;
				}
			}
			if (!flag) {
				CoachArea n = new CoachArea();
				//n.setDblink(user.getDblink());
				n.setCoachid(coachid);
				n.setAreaid(Integer.parseInt(ntype));
				n.setStep(param.getStep());
				addtypes.add(n);
			}
		}

		for (CoachArea type : all) {
			boolean flag = false;
			for (String ntype : newareas) {
				if (Integer.parseInt(ntype) == type.getAreaid()) {
					flag = true;
					break;
				}
			}
			if (!flag) {
				deltypes.add(type);
			}
		}

		if (addtypes.size() > 0) {
			Map params = new HashMap();
			params.put("list", addtypes);
			//params.put("dblink", user.getDblink());
			coachAreaMapper.batchAdd(params);
		}
		if (deltypes.size() > 0) {
			Map params = new HashMap();
			params.put("list", deltypes);
			//params.put("dblink", user.getDblink());
			coachAreaMapper.batchDel(params);
		}
	}

	private void updateClassinfo(String[] newclassinfos, Integer coachid
			) {
		CoachClassinfo param = new CoachClassinfo();
		//param.setDblink(user.getDblink());
		param.setCoachid(coachid);
		List<CoachClassinfo> all = coachClassinfoMapper.list(param);

		List<CoachClassinfo> addtypes = new ArrayList();
		List<CoachClassinfo> deltypes = new ArrayList();
		for (String ntype : newclassinfos) {
			boolean flag = false;
			for (CoachClassinfo type : all) {
				if (Integer.parseInt(ntype) == type.getClassinfoid()) {
					flag = true;
					break;
				}
			}
			if (!flag) {
				CoachClassinfo n = new CoachClassinfo();
				//n.setDblink(user.getDblink());
				n.setCoachid(coachid);
				n.setClassinfoid(Integer.parseInt(ntype));
				addtypes.add(n);
			}
		}

		for (CoachClassinfo type : all) {
			boolean flag = false;
			for (String ntype : newclassinfos) {
				if (Integer.parseInt(ntype) == type.getClassinfoid()) {
					flag = true;
					break;
				}
			}
			if (!flag) {
				deltypes.add(type);
			}
		}

		if (addtypes.size() > 0) {
			Map params = new HashMap();
			params.put("list", addtypes);
			//params.put("dblink", user.getDblink());
			coachClassinfoMapper.batchAdd(params);
		}
		if (deltypes.size() > 0) {
			Map params = new HashMap();
			params.put("list", deltypes);
			//params.put("dblink", user.getDblink());
			coachClassinfoMapper.batchDel(params);
		}
	}

	@Override
	public List<Coach> getCoachList(Coach coach) {
		/*
		 * PageUtil.startPage(coach); List<Coach> coachList =
		 * coachMapper.selectByCondition(coach); return new
		 * PageInfo<>(coachList);
		 */
		PageUtil.startPage(coach);
		List<Coach> coachList = coachMapper.selectByCondition(coach);
		
		try {
			CoachTeachType cty=new CoachTeachType();
//			User user = RequestContext.<User>get(ConstantUtil.USER_SESSION);
//			cty.setDblink(user.getDblink());
			List<CoachTeachType> teachtypes = settingService
					.listTeachType(cty);
			CoachJob cj=new CoachJob();
//			cj.setDblink(user.getDblink());
			List<CoachJob> jobs = settingService.listJob(cj);
			Area pa=new Area();
//			pa.setDblink(user.getDblink());
			List<Area> areas = areaService.selectAllList(pa);

			List<Integer> ids = new ArrayList<>();
			for (Coach c : coachList) {
				for (CoachTeachType type : teachtypes) {
					if (c.getTeachtypeid() != null
							&& c.getTeachtypeid().intValue() == type.getId()) {
						c.setTeachtype(type.getType());
						break;
					}
				}

				for (CoachJob job : jobs) {
					if (c.getJobid() != null
							&& c.getJobid().intValue() == job.getId()) {
						c.setJob(job.getJob());
						break;
					}
				}
				for (Area area : areas) {
					if (c.getAreaid() != null
							&& c.getAreaid().intValue() == area.getId()) {
						c.setArea(area.getName());
						break;
					}
				}

				ids.add(c.getCoachid());
			}
			if (ids.size() > 0) {
				Map<Integer, CoachClassinfo> coachClassMap = selectCoachClassBatch(
						ids);
				for (Coach c : coachList) {
					CoachClassinfo coachClassinfo = coachClassMap.get(c
							.getCoachid());
					if (c.getCoachid() != null && coachClassinfo != null) {
						c.setCoachclasses(coachClassinfo.getClassName());
						c.setCoachclassids(coachClassinfo.getIds());
					}
				}
			}
		} catch (Exception ex) {
			logger.error(ex);
		}

		return coachList;
	}

	@Override
	public List<Coach> getExportCoachList(Coach coach) {

		List<Coach> coachList = coachMapper.selectByCondition(coach);

		try {
			CoachTeachType cty=new CoachTeachType();
			//cty.setDblink(user.getDblink());
			List<CoachTeachType> teachtypes = settingService
					.listTeachType(cty);
			CoachJob cj=new CoachJob();
			//cj.setDblink(user.getDblink());
			List<CoachJob> jobs = settingService.listJob(cj);
			Area pa=new Area();
			//pa.setDblink(user.getDblink());
			List<Area> areas = areaService.selectAllList(pa);

			List<Integer> ids = new ArrayList<>();
			for (Coach c : coachList) {
				for (CoachTeachType type : teachtypes) {
					if (c.getTeachtypeid() != null
							&& c.getTeachtypeid().intValue() == type.getId()) {
						c.setTeachtype(type.getType());
						break;
					}
				}

				for (CoachJob job : jobs) {
					if (c.getJobid() != null
							&& c.getJobid().intValue() == job.getId()) {
						c.setJob(job.getJob());
						break;
					}
				}
				for (Area area : areas) {
					if (c.getAreaid() != null
							&& c.getAreaid().intValue() == area.getId()) {
						c.setArea(area.getName());
						break;
					}
				}

				ids.add(c.getCoachid());
			}
			if (ids.size() > 0) {
				Map<Integer, CoachClassinfo> coachClassMap = selectCoachClassBatch(
						ids);
				for (Coach c : coachList) {
					CoachClassinfo coachClassinfo = coachClassMap.get(c
							.getCoachid());
					if (c.getCoachid() != null && coachClassinfo != null) {
						c.setCoachclasses(coachClassinfo.getClassName());
						c.setCoachclassids(coachClassinfo.getIds());
					}
				}
			}
		} catch (Exception ex) {
			logger.error(ex);
		}

		return coachList;
	}

	@Override
	public List<Coach> getNoAssignCoachList(Coach coach) {
		/*
		 * PageUtil.startPage(coach); List<Coach> coachList =
		 * coachMapper.selectByCondition(coach); return new
		 * PageInfo<>(coachList);
		 */
		PageUtil.startPage(coach);
		List<Coach> coachList = coachMapper.selectNoassign(coach);

		try {
			CoachTeachType cty=new CoachTeachType();
			//cty.setDblink(user.getDblink());
			List<CoachTeachType> teachtypes = settingService
					.listTeachType(cty);
			CoachJob cj=new CoachJob();
			//cj.setDblink(user.getDblink());
			Area pa=new Area();
			//pa.setDblink(user.getDblink());
			List<CoachJob> jobs = settingService.listJob(cj);
			List<Area> areas = areaService.selectAllList(pa);

			List<Integer> ids = new ArrayList<>();
			for (Coach c : coachList) {
				for (CoachTeachType type : teachtypes) {
					if (c.getTeachtypeid() != null
							&& c.getTeachtypeid().intValue() == type.getId()) {
						c.setTeachtype(type.getType());
						break;
					}
				}

				for (CoachJob job : jobs) {
					if (c.getJobid() != null
							&& c.getJobid().intValue() == job.getId()) {
						c.setJob(job.getJob());
						break;
					}
				}
				for (Area area : areas) {
					if (c.getAreaid() != null
							&& c.getAreaid().intValue() == area.getId()) {
						c.setArea(area.getName());
						break;
					}
				}

				ids.add(c.getCoachid());
			}
			if (ids.size() > 0) {
				Map<Integer, CoachClassinfo> coachClassMap = selectCoachClassBatch(
						ids);
				for (Coach c : coachList) {
					CoachClassinfo coachClassinfo = coachClassMap.get(c
							.getCoachid());
					if (c.getCoachid() != null && coachClassinfo != null) {
						c.setCoachclasses(coachClassinfo.getClassName());
						c.setCoachclassids(coachClassinfo.getIds());
					}
				}
			}
		} catch (Exception ex) {
			logger.error(ex);
		}

		return coachList;
	}

	@Override
	public List<HeadCoach> getHeadCoachList(Coach coach) {
		//coach.setDblink(user.getDblink());
		PageUtil.startPage(coach);

		HeadCoach phc=new HeadCoach();
		phc.setDblink(coach.getDblink());
		List<HeadCoach> coachList = headCoachMapper.listHeadCoach(coach);
		List<HeadCoach> stats= headCoachMapper.statHeadCoach(phc);
		Area pa=new Area();
		//pa.setDblink(user.getDblink());
		List<Area> areas = areaService.selectAllList(pa);
		
		List<Integer> users=new ArrayList();
		
		for (HeadCoach hc : coachList) {
			
			for (Area area : areas) {
				if (hc.getAreaid() != null
						&& hc.getAreaid().intValue() == area.getId().intValue()) {
					hc.setArea(area.getName());
					break;
				}
			}
			for(HeadCoach s:stats){
				if(hc.getCoachid().intValue()==s.getCoachid()){
					hc.setOvercoach(s.getOvercoach());
					hc.setOvercoachcar(s.getOvercoachcar());
				}
			}
			users.add(hc.getMuserid());
		}
		
		if(users.size()>0){
		List<User> ulist=userService.selectMutiUser(users);
		for (HeadCoach hc : coachList) {
			for(User u:ulist){
				if(hc.getMuserid()==u.getId()){
					hc.setMuser(u.getUsername());
					break;
				}
			}
		}
		}
		return coachList;
	}

	@Override
	public ResultBean updateCoach(Coach coach,Map extendinfo) {
		ResultBean r = new ResultBean();
		Coach ca = new Coach();
		ca.setDblink(coach.getDblink());
		ca.setMobile(coach.getMobile());
		 List listm = coachMapper.selectByterm(ca);
		 if(listm.size()>0){
			 Coach cc = (Coach) listm.get(0);
			 //if(!coach.getIdcard().equals(cc.getIdcard())){
			 if(coach.getCoachid().intValue()!=cc.getCoachid().intValue()){
				r.setCode(3);
				r.setMsg("手机号已存在!");
				return r;
			 }
		 }
		Coach cac = new Coach();
		cac.setDblink(coach.getDblink());
		cac.setDrilicence(coach.getDrilicence());
		 List listd = coachMapper.selectByterm(cac);
		 if(listd.size()>0){
			 Coach cc = (Coach) listd.get(0);
			 //if(!coach.getIdcard().equals(cc.getIdcard())){
			 if(coach.getCoachid().intValue()!=cc.getCoachid().intValue()){
				r.setCode(3);
				r.setMsg("驾驶证号已存在!");
				return r;
			 }
		 }
		 
		 Coach cai = new Coach();
			cai.setDblink(coach.getDblink());
			cai.setIdcard(coach.getIdcard());
			 List listi = coachMapper.selectByterm(cai);
			 if(listi.size()>0){
				 Coach cc = (Coach) listi.get(0);
				 if(!coach.getCoachid().equals(cc.getCoachid())){
					r.setCode(3);
					r.setMsg("身份证已存在!");
					return r;
				 }
			 }
		coach.setMtime(new Date());
		
		Coach oldone=coachMapper.selectByPrimaryKey(coach);
		//Coach newone=new Coach();
		//BeanCopy.copyNotNull(oldone, newone);
		//BeanCopy.copyNotNull(oldone, newone);
		String detail= null;
		try{
			detail=LogCommonUtil.getChangeString(oldone, coach);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		LogCommon log = new LogCommon();
		log.setDetail(detail);
		log.setRelateid(coach.getCoachid()+"");
		log.setRelatetable("coach");
		log.setOperatetime(new Date());
		
		
		if(coach.getPhoto()==null||coach.getPhoto().length()==0){
			coach.setPhoto(oldone.getPhoto());
		}
		

		
		updateHeadCoach(oldone, coach, log,  r);
		if(r.getCode()!=0){
			return r;
		}
		
		coachMapper.updateByPrimaryKeySelective(coach);
		
		//log.setDblink(user.getDblink());
		logCommonService.insertLogInfo(log);
		if(extendinfo.containsKey("storeid")){
			String[] newstores=(String[])extendinfo.get("storeid");
			updateStore(newstores, coach.getCoachid());
		}else{//页面没有勾选，都取消
			updateStore(new String[]{}, coach.getCoachid());
		}
		if(extendinfo.containsKey("classinfoid")){
			String[] newclassinfoids=(String[])extendinfo.get("classinfoid");
			updateClassinfo(newclassinfoids, coach.getCoachid());
		}else{
			updateClassinfo(new String[]{}, coach.getCoachid());
		}
		if(extendinfo.containsKey("step2areaid")){
			String[] newstep2areaids=(String[])extendinfo.get("step2areaid");
			updateStep2Area(newstep2areaids, coach.getCoachid());
		}else{
			updateStep2Area(new String[]{}, coach.getCoachid());
		}
		if(extendinfo.containsKey("step3areaid")){
			String[] newstep3areaids=(String[])extendinfo.get("step3areaid");
			updateStep3Area(newstep3areaids, coach.getCoachid());
		}else{
			updateStep3Area(new String[]{}, coach.getCoachid());
		}
		//插入图片
		int id = coach.getCoachid();
		String array = coach.getImgeArray();
		if(!StringUtil.isNullString(array)){
			UploadImage ui = new UploadImage();
			ui.setBusinessId(id);
			ui.setModule("coach");
			//ui.setDblink(user.getDblink());
			String[] images = array.split(",");
			for (String key : images) {
				ui.setDblink(coach.getDblink());
				ui.setImgeKey(key);
				coachMapper.saveImage(ui);
			}
		}
		return r;
	}

	public void updateHeadCoach(Coach oldone,Coach coach,LogCommon log,ResultBean r){
		if(oldone.getHeadcoachflag()==null||oldone.getHeadcoachflag()==0){//之前不是组长
			if(coach.getHeadcoachflag()!=null&&coach.getHeadcoachflag()==1){//组长
				addHeadCoach(coach.getCoachid());
			}else{
				if(coach.getHeadcoachid()==null||coach.getHeadcoachid()==0){//当前的组长为空
					if(oldone.getHeadcoachid()!=null&&oldone.getHeadcoachid()!=0){//之前不为空
						//减少之前headcoach记录里的管理教练数
						String[] delcoachid=new String[]{ coach.getCoachid()+""};
						this.assignCoachProcess(oldone.getHeadcoachid(), null, delcoachid);
					}
				}else if(coach.getHeadcoachid()!=null&&coach.getHeadcoachid()!=0){//当前组长不为空
					if(oldone.getHeadcoachid()==null||oldone.getHeadcoachid()==0){//之前为空
						//增加之前headcoach记录里的管理教练数
						String[] coachid=new String[]{ coach.getCoachid()+""};
						this.assignCoachProcess(coach.getHeadcoachid(), coachid, null);
					}
				}
			}
		}else if(oldone.getHeadcoachflag()!=null&&oldone.getHeadcoachflag()==1){//之前是组长
			if(coach.getHeadcoachflag()==null||coach.getHeadcoachflag()==0){//修改成不是组长
				Coach hp=new Coach();
				//hp.setDblink(user.getDblink());
				hp.setHeadcoachid(coach.getCoachid());
				List<Coach> owncoaches= coachMapper.selectByCondition(hp);
				if(owncoaches!=null&&owncoaches.size()>0){//有管辖教练
					r.setCode(3);
					r.setMsg("此教练有管辖教练，不能修改成普通教练!");
					
				}else{
					HeadCoach dhp=new HeadCoach();
					//dhp.setDblink(user.getDblink());
					dhp.setCoachid(coach.getCoachid());
					headCoachMapper.delHeadCoach(dhp);
				}
			}
		}
	}
	
	@Override
	public ResultBean updateCoachTeachState(Coach coach) {
		ResultBean r = new ResultBean();
		coachMapper.updateTeachState(coach);
		return r;
	}

	@Override
	public ResultBean updateCoachEmploystatus(Coach coach) {
		ResultBean r = new ResultBean();
		coachMapper.updateEmploystatus(coach);
		return r;
	}

	@Override
	public void deleteById(Coach coach) {
		coachMapper.deleteByPrimaryKey(coach);
	}

	@Override
	public Coach getCoachById(Coach coach) {
		return coachMapper.selectByPrimaryKey(coach);
	}

	@Override
	public ResultBean getCoachModinfo(Coach coach, int applyid) {
		ResultBean resultBean = new ResultBean();
		Coach exist = coachMapper.selectByPrimaryKey(coach);
		CoachModApply param = new CoachModApply();
		//param.setDblink(user.getDblink());
		param.setId(applyid);
		CoachModApply apply = coachModApplyMapper.getApply(param);
		String moddetail = apply.getDetail();
		JSONObject json = JSONObject.parseObject(moddetail);
		// Coach mod= JSON.toJavaObject(json, Coach.class);
		copyJsonValueToBean(json, exist);
		User user = RequestContext.get(ConstantUtil.USER_SESSION);
		if (apply.getApplyuserid() == user.getId().intValue()) {// 当前用户是发起人
			if (apply.getStatus() == 0) {// 业务还在等待审核中
				exist.setModapplystat(ConstantUtil.AUDIT_RIGHT_CAN_CANCEL);
			} else {
				Flow flow = new Flow();
				//flow.setDblink(user.getDblink());
				flow.setTransactionid(apply.getTransactionid());
				FlowStep fs = flowService.getLastFlowStepByTransactionid(flow);
				if(fs==null){//没有审批流，可以直接审核
					exist.setModapplystat(ConstantUtil.AUDIT_RIGHT_CAN_AUDIT);
				}else{
					exist.setModapplystat(ConstantUtil.AUDIT_RIGHT_NONE);
				}
				
			}
		} else {
			Flow flow = new Flow();
			//flow.setDblink(user.getDblink());
			flow.setTransactionid(apply.getTransactionid());
			FlowStep fs = flowService.getLastFlowStepByTransactionid(flow);
			if(fs==null){//没有审批流，可以直接审核
				exist.setModapplystat(ConstantUtil.AUDIT_RIGHT_CAN_AUDIT);
			}else{
				if (fs.getUserid() == user.getId()) {// 业务流到当前用户，可以审核
					exist.setModapplystat(ConstantUtil.AUDIT_RIGHT_CAN_AUDIT);
				} else {
					exist.setModapplystat(ConstantUtil.AUDIT_RIGHT_NONE);
				}
			}
		}
		resultBean.setExtend(json);
		// exist.setModapplystat(apply.getStatus());
		resultBean.setResult(exist);

		return resultBean;
	}

	@Override
	public CoachModApply getModApply(CoachModApply param) {

		CoachModApply apply = coachModApplyMapper.getApplyByTransaction(param);
		User user=new User();
		user.setId(apply.getApplyuserid());;
		User applyuser= userService.getUser(user);
		apply.setAreaid(applyuser.getAreaid());
		return apply;
	}

	@Override
	public Map getCoachModExtendinfo(Coach coach, int applyid) {
		Coach exist = coachMapper.selectByPrimaryKey(coach);
		CoachModApply param = new CoachModApply();
		//param.setDblink(user.getDblink());
		param.setId(applyid);
		CoachModApply apply = coachModApplyMapper.getApply(param);
		String moddetail = apply.getDetail();
		JSONObject json = JSONObject.parseObject(moddetail);
		// Coach mod= JSON.toJavaObject(json, Coach.class);

		Map map = new HashMap();

		if (json.containsKey("storeid")) {
			StringBuffer buf = new StringBuffer();
			String storeid[] = StringUtil.strToArray(json.getString("storeid"));
			for (String one : storeid) {
				buf.append(one).append(",");
			}
			if (buf.length() > 0) {
				buf.deleteCharAt(buf.length() - 1);
			}
			map.put("storeid", buf.toString());
		}

		if (json.containsKey("classinfoid")) {
			StringBuffer buf = new StringBuffer();
			String classinfoid[] = StringUtil.strToArray(json
					.getString("classinfoid"));
			for (String one : classinfoid) {
				buf.append(one).append(",");
			}
			if (buf.length() > 0) {
				buf.deleteCharAt(buf.length() - 1);
			}
			map.put("classinfoid", buf.toString());
		}

		if (json.containsKey("step2areaid")) {
			StringBuffer buf2 = new StringBuffer();
			StringBuffer buf3 = new StringBuffer();
			String step2areaid[] = StringUtil.strToArray(json
					.getString("step2areaid"));
			String step3areaid[] = StringUtil.strToArray(json
					.getString("step3areaid"));

			for (String one : step2areaid) {
				buf2.append(one).append(",");
			}
			for (String one : step3areaid) {
				buf3.append(one).append(",");
			}

			if (buf2.length() > 0) {
				buf2.deleteCharAt(buf2.length() - 1);
			}
			if (buf3.length() > 0) {
				buf3.deleteCharAt(buf3.length() - 1);
			}
			map.put("step2areaid", buf2.toString());
			map.put("step3areaid", buf3.toString());
		}
		return map;

	}

	private void copyJsonValueToBean(JSONObject json, Coach coach) {

		Field[] fs = Coach.class.getDeclaredFields();
		java.util.Iterator<String> it = json.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			Object value = json.get(key);
			for (int i = 0; i < fs.length; i++) {
				Field f = fs[i];
				if (f.getName().equals(key)) {
					f.setAccessible(true);
					try {
						f.set(coach, value);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					break;
				}
			}
		}
	}

	@Override
	public Map getCoachExtendById(Coach coach) {
		Map map = new HashMap();
		CoachStore cs = new CoachStore();
		cs.setCoachid(coach.getCoachid());
		//cs.setDblink(user.getDblink());
		List<CoachStore> css = coachStoreMapper.list(cs);

		CoachClassinfo cci = new CoachClassinfo();
		//cci.setDblink(user.getDblink());
		cci.setCoachid(coach.getCoachid());
		List<CoachClassinfo> ccis = coachClassinfoMapper.list(cci);

		CoachArea ca = new CoachArea();
		//ca.setDblink(user.getDblink());
		ca.setCoachid(coach.getCoachid());
		List<CoachArea> cas = coachAreaMapper.list(ca);

		if (css != null) {
			StringBuffer buf = new StringBuffer();
			for (CoachStore one : css) {
				buf.append(one.getStoreid()).append(",");
			}
			if (buf.length() > 0) {
				buf.deleteCharAt(buf.length() - 1);
			}
			map.put("storeid", buf.toString());
		}

		if (ccis != null) {
			StringBuffer buf = new StringBuffer();
			for (CoachClassinfo one : ccis) {
				buf.append(one.getClassinfoid()).append(",");
			}
			if (buf.length() > 0) {
				buf.deleteCharAt(buf.length() - 1);
			}
			map.put("classinfoid", buf.toString());
		}

		if (cas != null) {
			StringBuffer buf2 = new StringBuffer();
			StringBuffer buf3 = new StringBuffer();
			for (CoachArea one : cas) {
				if (one.getStep() == 2)
					buf2.append(one.getAreaid()).append(",");
				else
					buf3.append(one.getAreaid()).append(",");
			}
			if (buf2.length() > 0) {
				buf2.deleteCharAt(buf2.length() - 1);
			}
			if (buf3.length() > 0) {
				buf3.deleteCharAt(buf3.length() - 1);
			}
			map.put("step2areaid", buf2.toString());
			map.put("step3areaid", buf3.toString());
		}
		return map;
	}

	@Override
	public void importCoach(Coach coach, List<Coach> list) {
		for (Coach c : list) {
			c.setDblink(coach.getDblink());
			coachMapper.insert(c);
		}
	}

	@Override
	public Integer getCount(Coach coach) {
		return coachMapper.getCount(coach);
	}

	@Override
	public ResultBean getNotStuListOfCoach(CoachStudentDTO coachStudentDTO) {
		ResultBean rb = new ResultBean();
		PageUtil.startPage(coachStudentDTO);
		List<CoachStudentDTO> list = coachMapper
				.getNotStuListOfCoach(coachStudentDTO);
		return getResultBean(list);
	}

	@Override
	public ResultBean getStuListOfCoach(CoachStudentDTO coachStudentDTO) {
		ResultBean rb = new ResultBean();
		PageUtil.startPage(coachStudentDTO);
		List<CoachStudentDTO> list = coachMapper
				.getStuListOfCoach(coachStudentDTO);
		return getResultBean(list);
	}

	public ResultBean getResultBean(List<CoachStudentDTO> list) {
		ResultBean rb = new ResultBean();
		Map<Long, CoachStudentDTO> map = new HashMap<Long, CoachStudentDTO>();
		for (CoachStudentDTO cs : list) {
			if (map.get(cs.getStudentid()) == null) {
				cs.setCoachNames(cs.getCoachName());
				map.put(cs.getStudentid(), cs);
			} else {
				CoachStudentDTO csd = map.get(cs.getStudentid());
				String name = cs.getCoachName();
				StringBuffer sb = new StringBuffer(csd.getCoachNames());
				sb.append(",").append(name);
				csd.setCoachNames(sb.toString());
				map.put(cs.getStudentid(), csd);
			}
		}
		List<CoachStudentDTO> listcs = new ArrayList<CoachStudentDTO>();
		Set<Entry<Long, CoachStudentDTO>> setEntry = map.entrySet();
		Iterator<Entry<Long, CoachStudentDTO>> it = setEntry.iterator();
		while (it.hasNext()) {
			Entry<Long, CoachStudentDTO> entry = it.next();
			listcs.add(entry.getValue());
		}
		rb.setResult(new PageInfo<>(listcs));
		return rb;
	}

//	@Override
//	public ResultBean updateCoachCar(int coachid, String carno,User user) {
//		ResultBean r = new ResultBean();
//		Coach coach = new Coach();
//		coach.setCoachid(coachid);
//		coach.setCarno(carno);
//		coach.setDblink(user.getDblink());
//		coachMapper.updateCarno(coach);
//		return r;
//	}

	@Override
	public ResultBean incrementCoachStudent(int coachid) {
		ResultBean r = new ResultBean();
		Coach coach = new Coach();
		coach.setCoachid(coachid);
		//coach.setDblink(user.getDblink());
		coachMapper.incrementCoachStudent(coach);
		return r;
	}

	@Override
	public ResultBean decrementCoachStudent(int coachid) {
		ResultBean r = new ResultBean();
		Coach coach = new Coach();
		coach.setCoachid(coachid);
		//coach.setDblink(user.getDblink());
		coachMapper.decrementCoachStudent(coach);
		return r;
	}

	@Override
	public ResultBean getStuAssignRecord(StudentAssign studentAssign)
			 {
		ResultBean rb = new ResultBean();
		List<StudentAssign> list = this.getAllStuAssignRecord(studentAssign);

		rb.setResult(new PageInfo<>(list));
		return rb;
	}

	@Override
	public List<StudentAssign> getAllStuAssignRecord(
			StudentAssign studentAssign) {

		List<StudentAssign> list = stuAssignMapper.list(studentAssign);
		Area pa=new Area();
		//pa.setDblink(user.getDblink());
		List<Area> areas = areaService.selectAllList(pa);
		Store ps=new Store();
		//ps.setDblink(user.getDblink());
		//User user = RequestContext.get(ConstantUtil.USER_SESSION);
		List<Store> stores = storeService.selectAllList(ps);
		
		CoachTeachType ctt= new CoachTeachType();
		//ctt.setDblink(user.getDblink());
		List<CoachTeachType> teachtypes = settingService
				.listTeachType(ctt);

		for (StudentAssign r : list) {
			for (Area area : areas) {
				if (r.getAreaid() != null
						&& r.getAreaid().intValue() == area.getId().intValue()) {
					r.setArea(area.getName());
					break;
				}
			}
			if (r.getStoreid() != null) {
				for (Store store : stores) {
					if (r.getStoreid().intValue() == store.getId().intValue()) {
						r.setStore(store.getName());
						break;
					}
				}
			} else {
				r.setStore("无");
			}
			for (CoachTeachType type : teachtypes) {
				if (r.getTeachtypeid() != null
						&& r.getTeachtypeid().intValue() == type.getId()) {
					r.setTeachtype(type.getType());
					break;
				}
			}
		}

		return list;
	}

	@Override
	public ResultBean modCoachApply(Coach coach, 
			Map extendsinfo, String businessid) {
		ResultBean rb = new ResultBean();

		CoachModApply param = new CoachModApply();
		//param.setDblink(user.getDblink());
		param.setCoachid(coach.getCoachid());

		CoachModApply eapply = coachModApplyMapper.getApplyByCoachid(param);
		if (eapply != null) {
			rb.setCode(ResultCode.ERRORCODE.COACHAPPLYEXIST);
			rb.setMsg(ResultCode.ERRORINFO.COACHAPPLYEXIST);
			return rb;
		}
		Coach exist = coachMapper.selectByPrimaryKey(coach);

		JSONObject detail = getModJson(coach, exist);

		if (extendsinfo.containsKey("storeid")) {
			String[] newstores = (String[]) extendsinfo.get("storeid");
			String storeid = StringUtil.arrayToStr(newstores);
			if (!"".equals(storeid)) {
				detail.put("storeid", storeid);
			}
		}
		if (extendsinfo.containsKey("classinfoid")) {
			String[] newclassinfoids = (String[]) extendsinfo
					.get("classinfoid");
			String classinfoid = StringUtil.arrayToStr(newclassinfoids);
			if (!"".equals(classinfoid)) {
				detail.put("classinfoid", classinfoid);
			}
		}
		if (extendsinfo.containsKey("step2areaid")) {
			String[] newstep2areaids = (String[]) extendsinfo
					.get("step2areaid");
			String step2areaid = StringUtil.arrayToStr(newstep2areaids);
			if (!"".equals(step2areaid)) {
				detail.put("step2areaid", step2areaid);
			}
		}
		if (extendsinfo.containsKey("step3areaid")) {
			String[] newstep3areaids = (String[]) extendsinfo
					.get("step3areaid");
			String step3areaid = StringUtil.arrayToStr(newstep3areaids);
			if (!"".equals(step3areaid)) {
				detail.put("step3areaid", step3areaid);
			}
		}

		CoachModApply apply = new CoachModApply();
		//apply.setDblink(user.getDblink());
		apply.setCoachid(coach.getCoachid());
		//apply.setApplyuserid(user.getId());
		apply.setDetail(detail.toJSONString());
		String desc = "教练[" + exist.getName() + "]修改申请";
		User user = RequestContext.get(ConstantUtil.USER_SESSION);
		String transactionid = flowService.addFlow(businessid, user.getId(),
				desc,user);
		apply.setBusinessid(businessid);
		apply.setApplyuser(user.getUsername());
		apply.setTransactionid(transactionid);
		coachModApplyMapper.addApply(apply);
		return rb;
	}

	private JSONObject getModJson(Coach newone, Coach oldone) {
		JSONObject json = new JSONObject();
		// if(newone.getAreaid()!=null)
		// json.put("areaid", newone.getAreaid());
		// else{
		// if(oldone.getAreaid()!=null){
		// json.put("areaid", "null");
		// }
		// }

		if (newone.getHeadcoachid() != null)
			json.put("headcoachid", newone.getHeadcoachid());
		else {
			if (oldone.getHeadcoachid() != null&&oldone.getHeadcoachid()!=0) {
				json.put("headcoachid", "null");
			}
		}

		if (newone.getTeachtypeid() != null) {
			if (oldone.getTeachtypeid() == null
					|| newone.getTeachtypeid().intValue() != oldone
							.getTeachtypeid().intValue()) {
				json.put("teachtypeid", newone.getTeachtypeid());
			}
		} else {
			if (oldone.getTeachtypeid() != null) {
				json.put("teachtypeid", "null");
			}
		}

		if (newone.getJobid() != null) {
			if (oldone.getJobid() == null
					|| newone.getJobid().intValue() != oldone.getJobid()
							.intValue()) {
				json.put("jobid", newone.getJobid());
			}
		} else {
			if (oldone.getJobid() != null) {
				json.put("jobid", "null");
			}
		}

		if (newone.getTeachcartype() != null) {
			if (!newone.getTeachcartype().equals(oldone.getTeachcartype())) {
				json.put("teachcartype", newone.getTeachcartype());
			}
		} else {
			if (oldone.getTeachcartype() != null) {
				json.put("teachcartype", "");
			}
		}

		if (newone.getStudentload() != null) {
			if (oldone.getStudentload() == null
					|| newone.getStudentload().intValue() != oldone
							.getStudentload().intValue()) {
				json.put("studentload", newone.getStudentload());
			}
		} else {
			if (oldone.getStudentload() != null) {
				json.put("studentload", "null");
			}
		}

		if (newone.getTeachstate() != null) {
			if (oldone.getTeachstate() == null
					|| newone.getTeachstate().intValue() != oldone
							.getTeachstate().intValue()) {
				json.put("teachstate", newone.getTeachstate());
			}
		} else {
			if (oldone.getTeachstate() != null) {
				json.put("teachstate", "null");
			}
		}

		if (newone.getMainstoreid() != null) {
			if (oldone.getMainstoreid() == null
					|| newone.getMainstoreid().intValue() != oldone
							.getMainstoreid().intValue()) {
				json.put("mainstoreid", newone.getMainstoreid());
			}
		} else {
			if (oldone.getMainstoreid() != null) {
				json.put("mainstoreid", "null");
			}
		}

		return json;
	}

	@Override
	public ResultBean listModCoachApply(CoachModApplyParam param) {
		if (param.getEnddate() != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(param.getEnddate());
			calendar.add(Calendar.DAY_OF_YEAR, 1);
			calendar.add(Calendar.SECOND, -1);
			param.setEnddate(calendar.getTime());
		}
		ResultBean rb = new ResultBean();
		PageUtil.startPage(param);
		List<CoachModApply> list = coachModApplyMapper.listApply(param);
		User up = new User();
		up.setSchoolid(param.getSchoolid());
		List<User> users = userService.selectSchoolUser(up);
		Area pa=new Area();
		//pa.setDblink(user.getDblink());
		List<Area> areas = areaService.selectAllList(pa);
		User user = RequestContext.get(ConstantUtil.USER_SESSION);
		for (CoachModApply apply : list) {
			for (Area area : areas) {
				if (apply.getAreaid() == area.getId()) {
					apply.setArea(area.getName());
					break;
				}
			}
			for (User s : users) {
				if (apply.getApplyuserid() == s.getId()) {
					apply.setApplyuser(s.getUsername());
				}
				if (apply.getAudituserid() == s.getId()) {
					apply.setAudituser(s.getUsername());
				}
			}
			
			if(apply.getApplyuserid()==user.getId()){
				apply.setModapplystat(ConstantUtil.AUDIT_RIGHT_CAN_CANCEL);
			}
		}
		rb.setResult(new PageInfo<>(list));
		return rb;
	}

	@Override
	public ResultBean updateModCoachApply(Coach coach, 
			Map extendsinfo,int applyid) {
		ResultBean rb = new ResultBean();

		Coach exist = coachMapper.selectByPrimaryKey(coach);

		CoachModApply param = new CoachModApply();
		//param.setDblink(user.getDblink());
		param.setId(applyid);

		CoachModApply apply = coachModApplyMapper.getApply(param);

		JSONObject detail = getModJson(coach, exist);
		User user = RequestContext.get(ConstantUtil.USER_SESSION);
		apply.setCoachid(coach.getCoachid());
		apply.setApplyuserid(user.getId());
		apply.setDetail(detail.toJSONString());
		apply.setDblink(coach.getDblink());
		coachModApplyMapper.updateApply(apply);
		return rb;
	}

	@Override
	public ResultBean auditModCoachApply(int applyid, int state) {
		ResultBean rb = new ResultBean();

		/**
		 * @todo 审核通过，数据没有改
		 */
		CoachModApply param = new CoachModApply();
		User user = RequestContext.get(ConstantUtil.USER_SESSION);
		//param.setDblink(user.getDblink());
		param.setId(applyid);
		CoachModApply apply = coachModApplyMapper.getApply(param);
		Flow flow = flowService.getFlow(apply.getTransactionid(),user);
		if (apply.getStatus() == 0) {
			apply.setStatus(state);
			apply.setAudituserid(user.getId());
			apply.setAudituser(user.getUsername());
			apply.setAuditdate(new Date());
		} else {
			rb.setCode(ResultCode.ERRORCODE.APPLYHASAUDIT);
			rb.setMsg(ResultCode.ERRORINFO.APPLYHASAUDIT);
			return rb;
		}
		
		
		//flow.setDblink(user.getDblink());
		
		flow.setTransactionid(apply.getTransactionid());
		FlowStep fs = flowService.getLastFlowStepByTransactionid(flow);
		if(fs!=null){//有审批流
			if(state!=3){//审核
				rb.setCode(ResultCode.ERRORCODE.NOAUTH);
				rb.setMsg(ResultCode.ERRORINFO.NOAUTH);
				return rb;
			}
		}
		
		//apply.setDblink(user.getDblink());
		
		auditProcess(apply, flow, state, rb);
		return rb;
	}

	@Override
	public ResultBean batchAuditModCoachApply(String[] applyid, int state
			) {
		ResultBean rb = new ResultBean();
		User user = RequestContext.get(ConstantUtil.USER_SESSION);
		for (String id : applyid) {
			CoachModApply param = new CoachModApply();
			//param.setDblink(user.getDblink());
			param.setId(Integer.parseInt(id));
			CoachModApply apply = coachModApplyMapper.getApply(param);
			Flow flow = flowService.getFlow(apply.getTransactionid(),user);
			if (apply.getStatus() == 0) {
				apply.setStatus(state);
				apply.setAudituserid(user.getId());
				apply.setAudituser(user.getUsername());
				apply.setAuditdate(new Date());
			} else {

			}
			//flow.setDblink(user.getDblink());
			//apply.setDblink(user.getDblink());
			auditProcess(apply, flow, state, rb);
			
//			if (state == 1) {
//				boolean next = (flow != null)
//						&& flowService.auditFlow(flow, user.getId(),
//								ConstantUtil.AUDIT_ACCEPT);
//				if (!next) {
//					coachModApplyMapper.updateApplyStatus(apply);
//				}
//			} else {
//				coachModApplyMapper.updateApplyStatus(apply);
//			}
		}
		return rb;
	}
	
	public void auditProcess(CoachModApply apply,Flow flow,int state, 
			ResultBean rb1){
		User user = RequestContext.get(ConstantUtil.USER_SESSION);
		if (state == 1) {
			boolean next = (flow != null)
					&& flowService.auditFlow(flow, user.getId(),
							ConstantUtil.AUDIT_ACCEPT);
			
			if (!next) {
				Coach pc=new Coach(apply
						.getCoachid());
				//pc.setDblink(user.getDblink());
				Coach exist = coachMapper.selectByPrimaryKey(pc);
				//exist.setDblink(user.getDblink());
				JSONObject jsonobject = JSONObject.parseObject(apply
						.getDetail());
				Iterator<String> keys = jsonobject.keySet().iterator();
				Map extendinfo = new HashMap();
				Integer oldheadcoachid=exist.getHeadcoachid();
				while (keys.hasNext()) {
					String key = keys.next();
					Object value = jsonobject.get(key);
					try {
						if (!key.equals("storeid")
								&& !key.equals("classinfoid")
								&& !key.equals("step2areaid")
								&& !key.equals("step3areaid")) {
							if(value.equals("null")){
								if(Coach.class.getDeclaredField(key).getGenericType().toString().equals("class java.lang.Integer")){
									PropertyUtils.setProperty(exist, key, null);
								}
								//("class java.lang.String")
							}else{
								PropertyUtils.setProperty(exist, key, value);
							}
							
						} else {
							extendinfo.put(key,
									StringUtil.strToArray((String) value));
						}
					} catch (Exception ex) {
						logger.warn(ex);
					}
				}
				
				Integer newheadcoachid=null;
				if(jsonobject.containsKey("headcoachid")){//组长属性变动
					String newvalue= jsonobject.getString("headcoachid");
					if(newvalue==null||newvalue.length()==0||newvalue.equals("null")){
						newheadcoachid=null;
					}else{
						newheadcoachid=jsonobject.getInteger("headcoachid");
					}
					
				}
				
				if(newheadcoachid==null||newheadcoachid==0){//当前的组长为空
					if(oldheadcoachid!=null&&oldheadcoachid!=0){//之前不为空
						//减少之前headcoach记录里的管理教练数
						String[] delcoachid=new String[]{ exist.getCoachid()+""};
						this.assignCoachProcess(oldheadcoachid, null, delcoachid);
					}
				}else if(newheadcoachid!=null&&newheadcoachid!=0){//当前组长不为空
					if(oldheadcoachid==null||oldheadcoachid==0){//之前为空
						//增加之前headcoach记录里的管理教练数
						String[] coachid=new String[]{ exist.getCoachid()+""};
						this.assignCoachProcess(newheadcoachid, coachid, null);
					}
				}
				
				coachMapper.updateByPrimaryKeySelective(exist);
					
				if (extendinfo.containsKey("storeid")) {
					String[] newstores = (String[]) extendinfo.get("storeid");
					updateStore(newstores, exist.getCoachid());
				} else {// 页面没有勾选，都取消
					updateStore(new String[] {}, exist.getCoachid());
				}
				if (extendinfo.containsKey("classinfoid")) {
					String[] newclassinfoids = (String[]) extendinfo
							.get("classinfoid");
					updateClassinfo(newclassinfoids, exist.getCoachid());
				} else {
					updateClassinfo(new String[] {}, exist.getCoachid());
				}
				if (extendinfo.containsKey("step2areaid")) {
					String[] newstep2areaids = (String[]) extendinfo
							.get("step2areaid");
					updateStep2Area(newstep2areaids, exist.getCoachid());
				} else {
					updateStep2Area(new String[] {}, exist.getCoachid());
				}
				if (extendinfo.containsKey("step3areaid")) {
					String[] newstep3areaids = (String[]) extendinfo
							.get("step3areaid");
					updateStep3Area(newstep3areaids, exist.getCoachid());
				} else {
					updateStep3Area(new String[] {}, exist.getCoachid());
				}

				coachModApplyMapper.updateApplyStatus(apply);
			}
		} else if(state==2){//审核不通过
			if(flow!=null)
			flowService.auditFlow(flow, user.getId(),
					ConstantUtil.AUDIT_REJECT);
			coachModApplyMapper.updateApplyStatus(apply);
		} else if(state==3){
			if(flow!=null)
			flowService.auditFlow(flow, user.getId(),
					ConstantUtil.AUDIT_CANCEL);
			coachModApplyMapper.updateApplyStatus(apply);
		}
	}

	@Override
	public ResultBean assignCoach(int headcoachid, String coachid[],
			String delcoachid[]) {
		ResultBean rb = new ResultBean();
		
		assignCoachProcess(headcoachid, coachid, delcoachid);
		
		return rb;
	}

	/**
	 * 教学组长变动处理
	 * @param headcoachid
	 * @param coachid
	 * @param delcoachid
	 * @param log
	 * @param user
	 */
	public void assignCoachProcess(int headcoachid, String coachid[],
			String delcoachid[]) {
		List list = new ArrayList();
		List dellist = new ArrayList();
		if (coachid != null)
			for (String id : coachid) {
				Coach coach = new Coach();
				coach.setCoachid(Integer.parseInt(id));
				coach.setHeadcoachid(headcoachid);
				list.add(coach);
			}
		if (delcoachid != null)
			for (String id : delcoachid) {
				Coach coach = new Coach();
				coach.setCoachid(Integer.parseInt(id));
				coach.setHeadcoachid(0);
				dellist.add(coach);
			}
		
		User user = RequestContext.get(ConstantUtil.USER_SESSION);
		Map params = new HashMap();
		if (list.size() > 0) {
			params.put("list", list);
			
			params.put("headcoachid", headcoachid);
			
			coachMapper.updateHeadcoach(params);
			int carcount = coachMapper.getCoachcarcount(params);

			HeadCoach headCoach = new HeadCoach();
			headCoach.setCoachid(headcoachid);
			headCoach.setOvercoach(coachid.length);
			headCoach.setOvercoachcar(carcount);
			headCoach.setMuserid(user.getId());
			
			headCoachMapper.updateHeadCoachData(headCoach);
		}
		if (dellist.size() > 0) {
			params.put("list", dellist);
			
			params.put("headcoachid", 0);
			coachMapper.updateHeadcoach(params);
			int carcount = coachMapper.getCoachcarcount(params);

			HeadCoach headCoach = new HeadCoach();
			headCoach.setCoachid(headcoachid);
			headCoach.setOvercoach(-delcoachid.length);
			headCoach.setOvercoachcar(-carcount);
			headCoach.setMuserid(user.getId());
			
			headCoachMapper.updateHeadCoachData(headCoach);
		}
	}
	
	@Override
	public ResultBean batchUpdateCoach(String coachid[], Coach coach,
			String classinfoid[]) {
		ResultBean rb = new ResultBean();

		List list = new ArrayList();
		for (String id : coachid) {
			Coach c = new Coach();
			c.setCoachid(Integer.parseInt(id));
			c.setTeachtypeid(coach.getTeachtypeid());
			c.setStudentload(coach.getStudentload());
			list.add(c);
		}

		if (classinfoid != null && classinfoid.length > 0) {
			for (String id : coachid) {
				updateClassinfo(classinfoid, Integer.parseInt(id));
			}
		} else {
			// for(String id:coachid){
			// updateClassinfo(new String[]{}, Integer.parseInt(id), user);
			// }
		}

		Map params = new HashMap();
		params.put("list", list);
		//params.put("dblink", user.getDblink());
		params.put("teachtypeid", coach.getTeachtypeid());
		params.put("studentload", coach.getStudentload());
		if (coach.getTeachtypeid() != null && coach.getTeachtypeid() > 0) {
			coachMapper.batchUpdateTeachType(params);
		}
		if (coach.getStudentload() != null) {
			coachMapper.batchUpdateStudentLoad(params);
		}

		return rb;
	}

	@Override
	public Map<Integer, CoachClassinfo> selectCoachClassBatch(
			List<Integer> ids) {
		Map<String, Object> map = new HashMap<>();
		//map.put("dblink", user.getDblink());
		map.put("list", ids);
		List<CoachClassinfo> ccis = coachClassinfoMapper
				.selectCoachClassBatch(map);
		Map<Integer, CoachClassinfo> coachClassMap = new HashMap<>();
		for (CoachClassinfo cci : ccis) {
			CoachClassinfo coachClassinfo = coachClassMap.get(cci.getCoachid());
			if (coachClassinfo == null) {
				coachClassinfo = new CoachClassinfo();
				coachClassinfo.setIds(cci.getClassinfoid().toString());
				coachClassinfo.setClassName(cci.getClassName());
			} else {
				coachClassinfo.setIds(coachClassinfo.getIds() + ","
						+ cci.getClassinfoid().toString());
				coachClassinfo.setClassName(coachClassinfo.getClassName() + "/"
						+ cci.getClassName());
			}
			coachClassMap.put(cci.getCoachid(), coachClassinfo);
		}
		return coachClassMap;
	}

	@Override
	public List<CoachLoadStudentInfo> getCoachLoadStudentInfo(
			CoachLoadStudentInfo info) {
		return coachMapper.getCoachLoadStudentInfo(info);
	}

	@Override
	public Map<Integer, Integer> getStoreCoachNumBatch(List<Integer> storeids) {
		Map<String, Object> map = new HashMap<>();
		//map.put("dblink", user.getDblink());
		map.put("list", storeids);
		List<Store> list = coachStoreMapper.batchSelectCount(map);
		Map<Integer, Integer> result = new HashMap<>();
		for (Store s : list) {
			result.put(s.getId(), s.getCoachnum());
		}
		return result;
	}

	@Override
	public ResultBean assignList(Coach coach, Integer studentid) {
		ResultBean r = new ResultBean();
		try {
			Student s = new Student();
			s.setId(studentid);
			//s.setDblink(user.getDblink());
			s = studentService.getStudent(s);
			if (s == null)
				return r;
			//20170401 愚人节 去除分配教练列表的筛选条件
			//20170418 重新加入筛选条件
			coach.setClassid(s.getClassid());
			coach.setAreaid(s.getAreaid());
			coach.setTeachcartype(s.getTraintype());
			coach.setTeachstate(1); //分配状态，1正常分配
			coach.setEmploystatus("0"); //供职状态， 0在职
			coach.setStudentid(studentid);
			List<Coach> list = getCoachList(coach);
			r.setResult(new PageInfo<>(list));
		} catch (Exception e) {
			e.printStackTrace();
			r.setCode(HttpConstant.ERROR_CODE);
			r.setMsg(HttpConstant.ERROR_MSG);
		}
		return r;
	}

}

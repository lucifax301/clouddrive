package cn.com.liliyun.car.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.liliyun.car.mapper.CarCostRemindMapper;
import cn.com.liliyun.car.mapper.CarLogMapper;
import cn.com.liliyun.car.mapper.CarMapper;
import cn.com.liliyun.car.mapper.CarMileageMapper;
import cn.com.liliyun.car.mapper.CarPartsMapper;
import cn.com.liliyun.car.mapper.CarScrapMapper;
import cn.com.liliyun.car.mapper.PartsSettingMapper;
import cn.com.liliyun.car.model.Car;
import cn.com.liliyun.car.model.CarCostRemind;
import cn.com.liliyun.car.model.CarLog;
import cn.com.liliyun.car.model.CarMileage;
import cn.com.liliyun.car.model.CarParts;
import cn.com.liliyun.car.model.CarPartsNotice;
import cn.com.liliyun.car.model.CarRemind;
import cn.com.liliyun.car.model.CarScrap;
import cn.com.liliyun.car.model.PartsSetting;
import cn.com.liliyun.car.service.CarBizService;
import cn.com.liliyun.common.dto.MapDTO;
import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.common.util.ConstantUtil;
import cn.com.liliyun.common.util.HttpConstant;
import cn.com.liliyun.common.util.PageUtil;
import cn.com.liliyun.flow.model.Flow;
import cn.com.liliyun.flow.model.FlowStep;
import cn.com.liliyun.flow.service.FlowService;
import cn.com.liliyun.trainorg.model.Area;
import cn.com.liliyun.trainorg.service.AreaService;
import cn.com.liliyun.user.model.User;

import com.github.pagehelper.PageInfo;

@Service
public class CarBizServiceImpl implements CarBizService {

	private Logger logger = Logger.getLogger(CarBizServiceImpl.class);

	@Autowired
	private CarCostRemindMapper carCostRemindMapper;
	
	@Autowired
	private CarMileageMapper carMileageMapper;
	
	@Autowired
	private CarPartsMapper carPartsMapper;
	
	@Autowired
	private PartsSettingMapper partsSettingMapper;
	
	@Autowired
	private CarScrapMapper carScrapMapper;

	@Autowired
	private CarLogMapper carLogMapper;
	
	@Autowired
	private CarMapper carMapper;
	
	@Autowired
	private AreaService areaService;
	
	@Autowired
	private FlowService flowService;
	
	
	
	@Override
	public CarCostRemind getCostRemind(CarCostRemind costRemind) {
		return carCostRemindMapper.selectCost(costRemind);
	}

	@Override
	public ResultBean updateCostRemind(CarCostRemind costRemind) {
		CarCostRemind exist= carCostRemindMapper.selectCost(costRemind);
		if(exist!=null){
			carCostRemindMapper.updateSelective(costRemind);
		}else{
			carCostRemindMapper.insertSelective(costRemind);
		}
		return new ResultBean();
	}

	@Override
	public List<CarMileage> listMileage(CarMileage mileage) {
		PageUtil.startPage(mileage);
		return carMileageMapper.selectList(mileage);
	}

	@Override
	public CarMileage getMileage(CarMileage mileage) {
		return carMileageMapper.selectByPrimaryKey(mileage);
	}

	@Override
	public ResultBean editMileage(User user,CarMileage mileage) {
		ResultBean rb = new ResultBean();
		mileage.setCuid(user.getId());
		mileage.setCname(user.getRealname());
		mileage.setCtime(new Date());
		if (mileage.getId() == null) {
			carMileageMapper.insertSelective(mileage);
		} else {
			carMileageMapper.updateByPrimaryKeySelective(mileage);
		}
		return rb;
	}

	@Override
	public ResultBean getCarPartsList(CarParts carParts, User user) {
		ResultBean r = new ResultBean();
		
		carParts.setDblink(user.getDblink());
		PageUtil.startPage(carParts);
		List<CarParts> list = carPartsMapper.selectList(carParts);
		r.setResult(new PageInfo<>(list));
		
		return r;
	}

	@Override
	public ResultBean addCarParts(CarParts carParts, User user) {
		ResultBean r = new ResultBean();
		
		if (carParts.getCarid() == null || carParts.getPartstype() == null || carParts.getHandletime() == null){
			r.setCode(HttpConstant.DATA_ERROR_COCE);
			r.setMsg(HttpConstant.DATA_ERROR_MSG);
			return r;
		}
		boolean iscurrent = true, islatest = true;
		CarParts cp = new CarParts();
		cp.setDblink(user.getDblink());
		cp.setCarid(carParts.getCarid());
		cp.setIscurrent((byte) 1);
		List<CarParts> cpCurrents = carPartsMapper.selectList(cp);
		if (cpCurrents != null && cpCurrents.size() > 0) {
			for (CarParts cpCurrent : cpCurrents ) {
				if (cpCurrent.getHandletime().after(carParts.getHandletime()))
					iscurrent = false;
			}
		}
		//去掉车辆的旧记录的当前记录标识
		if (iscurrent) {
			cp.setIscurrent((byte) 0);
			carPartsMapper.updateByCarOrTypeSelective(cp);
		}
		
		cp.setPartstype(carParts.getPartstype());
		cp.setIscurrent(null);
		cp.setIslatest((byte) 1);
		List<CarParts> cpLatests = carPartsMapper.selectList(cp);
		if (cpLatests != null && cpLatests.size() > 0) {
			for (CarParts cpLatest : cpLatests ) {
				if (cpLatest.getHandletime().after(carParts.getHandletime()))
					islatest = false;
			}
		}
		//去掉车辆的当前更换配件旧记录的最新记录标识
		if (islatest) {
			cp.setIslatest((byte) 0);
			carPartsMapper.updateByCarOrTypeSelective(cp);
		}
		
		carParts.setDblink(user.getDblink());
		carParts.setCuid(user.getId());
		carParts.setCname(user.getRealname());
		//设置记录为当前以及对应配件最新的记录
		carParts.setIscurrent((byte) (iscurrent?1:0));
		carParts.setIslatest((byte) (islatest?1:0));
		carPartsMapper.insertSelective(carParts);
		
		return r;
	}

	@Override
	public List<CarParts> getCarPartsExport(CarParts carParts, User user) {
		return carPartsMapper.selectList(carParts);
	}

	@Override
	public List<PartsSetting> getPartsSettings(User user) {
		PartsSetting partsSetting = new PartsSetting();
		partsSetting.setDblink(user.getDblink());
		return partsSettingMapper.selectAll(partsSetting);
	}

	@Override
	public ResultBean setPartsSetting(List<PartsSetting> partsSettings, User user) {
		ResultBean r = new ResultBean();
		
		List addlist=new ArrayList();
		List updatelist=new ArrayList();
		for(PartsSetting ps: partsSettings){
			ps.setDblink(user.getDblink());
			PartsSetting exist= partsSettingMapper.selectByPrimaryKey(ps);
			if(exist!=null){
				updatelist.add(ps);
			}else{
				addlist.add(ps);
			}
		}
		
		if(addlist.size()>0){
			Map<String, Object> map = new HashMap<>();
			map.put("dblink", user.getDblink());
			map.put("list", addlist);
			
			partsSettingMapper.batchInsertSelective(map);
		}
		
		if(updatelist.size()>0){
			Map<String, Object> map = new HashMap<>();
			map.put("dblink", user.getDblink());
			map.put("list", updatelist);
			
			partsSettingMapper.updateByPrimaryKeySelectiveBatch(map);
		}
			
		return r;
	}

	@Override
	public ResultBean getPartsNotice(PartsSetting partsSetting, User user) {
		ResultBean r = new ResultBean();
		
		partsSetting.setDblink(user.getDblink());
		List<PartsSetting> partsSettings = new ArrayList<>();
		if (partsSetting.getPartstype() != null) {
			partsSettings.add(partsSettingMapper.selectByPrimaryKey(partsSetting));
		} else {
			partsSettings.addAll(partsSettingMapper.selectAll(partsSetting));
		}
		PageUtil.startPage(partsSetting);
		Map<String, Object> map = new HashMap<>();
		map.put("dblink", user.getDblink());
		map.put("list", partsSettings);
		if(partsSettings.size()>0){
			List<CarPartsNotice> list = carPartsMapper.selectPartsNotice(map);
			r.setResult(new PageInfo<>(list));
		}else{
			r.setResult(new PageInfo<>(new ArrayList()));
		}
		
		return r;
	}

	@Override
	public List<CarLog> getCarLogList(CarLog carLog) {
		Area area =new Area();
		area.setDblink(carLog.getDblink());
	   	Map <Integer,MapDTO> areas = areaService.getMap(area);
	   	PageUtil.startPage(carLog);
	   	List<CarLog> result=carLogMapper.selectList(carLog);
	   	for(CarLog c:result){
	   		c.setAreaName(areas.get(c.getAreaid()) != null ? areas.get(c.getAreaid()).getName() : "");
	   	}
		return result;
	}

	@Override
	public List<CarScrap> listScrap(CarScrap carScrap) {
		PageUtil.startPage(carScrap);
		return carScrapMapper.selectList(carScrap);
	}

	@Override
	public ResultBean editScrap(User user, CarScrap carScrap) {
		ResultBean rb = new ResultBean();
		carScrap.setCuid(user.getId());
		carScrap.setCname(user.getRealname());
		carScrap.setCtime(new Date());
		carScrap.setType(1);
		carScrapMapper.insertSelective(carScrap);
		Car car = new Car();
		car.setDblink(user.getDblink());
		car.setCarId(carScrap.getCarid());
		car.setAcceptDate(carScrap.getAcceptdate());
		car.setRetireDate(carScrap.getRetiredate());
		carMapper.updateByPrimaryKeySelective(car);
		return rb;
	}

	@Override
	public ResultBean addDelay(User user, CarScrap carScrap) {
		ResultBean rb = new ResultBean();
		carScrap.setCuid(user.getId());
		carScrap.setCname(user.getRealname());
		carScrap.setCtime(new Date());
		carScrap.setType(0);
		carScrapMapper.insertSelective(carScrap);
		Car car = new Car();
		car.setDblink(user.getDblink());
		car.setCarId(carScrap.getCarid());
		car.setDelayScrap(1);
		carMapper.updateByPrimaryKeySelective(car);
		return rb;
	}

	@Override
	public ResultBean editDelay(User user, CarScrap carScrap) {
		ResultBean rb = new ResultBean();
		carScrapMapper.updateByPrimaryKeySelective(carScrap);
		return rb;
	}

	@Override
	public List<CarRemind> listCarRemind(User user,CarCostRemind carCostRemind) {
		CarCostRemind query = carCostRemindMapper.selectCost(carCostRemind);
		if(query!=null){
		carCostRemind.setAnnualcheck(query.getAnnualcheck());
		carCostRemind.setInsurance(query.getInsurance());
		carCostRemind.setTax(query.getTax());
		carCostRemind.setRetire(query.getRetire());
		}
		PageUtil.startPage(carCostRemind);
		return carCostRemindMapper.selectRemindList(carCostRemind);
	}

	@Override
	public ResultBean editCarParts(CarParts carParts, User user) {
		ResultBean rb = new ResultBean();
		carPartsMapper.updateByPrimaryKeySelective(carParts);
		return rb;
	}

	@Override
	public Integer getCount(Car car) {
		return carMapper.getCount(car);
	}

	@Override
	public ResultBean addLog(CarLog carLog,User user, String bussinessid) {
		ResultBean rb = new ResultBean();
		String desc="车辆["+carLog.getCarno()+"]调动申请";
		String transactionid= flowService.addFlow(bussinessid, user.getId(),desc,user);
		carLog.setCarid(user.getId());
		carLog.setCname(user.getRealname());
		carLog.setCtime(new Date());
		carLog.setAuditStatus(1);//办理中
		carLog.setSubmitId(user.getId());
		carLog.setTransactionId(transactionid);
		carLog.setType(4);//调动
		//查询车最近一次的变更时间
		CarLog query=new CarLog();
		query.setCarno(carLog.getCarno());
		List<CarLog> carLogList=carLogMapper.selectList(query);
		if(carLogList!=null&&carLogList.size()>0){
			CarLog cl=carLogList.get(0);
			carLog.setLeadDate(cl.getCtime());
		}else{
			carLog.setLeadDate(new Date());
		}
		//查询更换车上次的变更时间
		query.setCarno(carLog.getCarno());
		List<CarLog> changeCarLogList=carLogMapper.selectList(query);
		if(changeCarLogList!=null&&changeCarLogList.size()>0){
			CarLog cl=changeCarLogList.get(0);
			carLog.setChangeLeadDate(cl.getCtime());
		}else{
			carLog.setChangeLeadDate(new Date());
		}
		carLogMapper.insertSelective(carLog);
		return rb;
	}

	@Override
	public CarLog getLog(CarLog carLog,User user) {
		CarLog cl=carLogMapper.selectByPrimaryKey(carLog);
		if(cl.getSubmitId().equals(user.getId().intValue())){
			if (carLog.getAuditStatus() == 1) {// 业务还在等待审核中
				cl.setModapplystat(ConstantUtil.AUDIT_RIGHT_CAN_CANCEL);
			} else {
				cl.setModapplystat(ConstantUtil.AUDIT_RIGHT_NONE);
			}
		}else{
			Flow flow = new Flow();
			flow.setDblink(user.getDblink());
			flow.setTransactionid(cl.getTransactionId());
			FlowStep fs = flowService.getLastFlowStepByTransactionid(flow);
			if(fs==null){//没有审批流，可以直接审核
				cl.setModapplystat(ConstantUtil.AUDIT_RIGHT_CAN_AUDIT);
			}else{
				flow.setId(fs.getFlowid());
				if (fs.getUserid() == user.getId()) {// 业务流到当前用户，可以审核
					cl.setModapplystat(ConstantUtil.AUDIT_RIGHT_CAN_AUDIT);
				} else {
					cl.setModapplystat(ConstantUtil.AUDIT_RIGHT_NONE);
				}
			}
		}
		return cl;
	}

	@Override
	public void updateAuditLog(CarLog carLog, User user, String bussinessid,
			int i) {
		CarLog cl=carLogMapper.selectByPrimaryKey(carLog);
		Flow flow= flowService.getFlow(cl.getTransactionId(),user);
		if(i==1){//通过审批
			if(cl.getAuditStatus()==1){
				flow.setDblink(user.getDblink());
				boolean next=(flow!=null)&&flowService.auditFlow(flow, user.getId(), ConstantUtil.AUDIT_ACCEPT);
				if(!next){
					Car queryCar=new Car();
					queryCar.setCarNo(cl.getCarno());
					Car car=carMapper.selectByCarNo(queryCar);
					car.setAreaId(cl.getCurAreaid());
					car.setCoachName(cl.getName());
					carMapper.updateByPrimaryKeySelective(car);
					if(cl.getChangeOrNot()==1){//如果换车
						queryCar.setCarNo(cl.getChangeCarNo());
						Car changeCar=carMapper.selectByCarNo(queryCar);
						changeCar.setAreaId(cl.getCurChangeAreaId());
						changeCar.setCoachName(cl.getCurChangeName());
						carMapper.updateByPrimaryKeySelective(changeCar);
					}
					cl.setAuditStatus(3);//审批结束
					carLogMapper.updateByPrimaryKeySelective(cl);
				}
			}
		}else if(i==2){//拒绝审批
			boolean next=(flow!=null)&&flowService.auditFlow(flow, user.getId(), ConstantUtil.AUDIT_REJECT);
			if(!next){
				cl.setAuditStatus(2);
				carLogMapper.updateByPrimaryKeySelective(cl);
			}
		}
		
	}

}

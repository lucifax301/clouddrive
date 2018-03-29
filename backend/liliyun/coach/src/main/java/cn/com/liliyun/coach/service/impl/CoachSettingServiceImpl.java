package cn.com.liliyun.coach.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.liliyun.coach.mapper.CarTypeMapper;
import cn.com.liliyun.coach.mapper.ClassTypeMapper;
import cn.com.liliyun.coach.mapper.JobMapper;
import cn.com.liliyun.coach.mapper.TeachTypeMapper;
import cn.com.liliyun.coach.model.CoachCarType;
import cn.com.liliyun.coach.model.CoachClassType;
import cn.com.liliyun.coach.model.CoachJob;
import cn.com.liliyun.coach.model.CoachTeachType;
import cn.com.liliyun.coach.service.CoachSettingService;
import cn.com.liliyun.common.CommonService;
import cn.com.liliyun.common.exception.BizException;
import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.common.util.BeanCopy;
import cn.com.liliyun.common.util.PageUtil;
import cn.com.liliyun.log.model.LogCommon;
import cn.com.liliyun.student.model.Student;
import cn.com.liliyun.student.service.StudentService;
import cn.com.liliyun.trainorg.model.Classinfo;
import cn.com.liliyun.trainorg.service.ClassinfoService;
//import cn.com.liliyun.log.util.LogCommonUtil;
import cn.com.liliyun.user.model.User;
import cn.com.liliyun.user.service.UserService;

@SuppressWarnings("rawtypes")
@Service
public class CoachSettingServiceImpl extends CommonService implements CoachSettingService {

	private static Logger logger=Logger.getLogger(CoachSettingService.class);
	
	@Autowired
	private TeachTypeMapper teachTypeMapper;
	
	@Autowired
	private CarTypeMapper carTypeMapper;
	
	@Autowired
	private ClassTypeMapper classTypeMapper;
	
	@Autowired
	private JobMapper jobMapper;
	
	@Autowired
	private ClassinfoService classinfoService;
	
//	@Autowired
//	private StudentService studentService;
	
	@Autowired
	private UserService userService;
	
	
	
	@Override
	public void addTeachType(CoachTeachType type,String[] subject) {
		
			if(subject!=null){
				StringBuilder builder=new StringBuilder();
				for(int i=0;i<subject.length;i++){
					builder.append(subject[i]).append(",");
				}
				if(builder.length()>0){
					builder.deleteCharAt(builder.length()-1);
				}
				type.setSubject(builder.toString());
			}
			teachTypeMapper.addTeachType(type);
		
	}

	@Override
	public void updateTeachType(CoachTeachType type,String[] subject)  {
		try{
			CoachTeachType oldone= teachTypeMapper.getTeachType(type);
			CoachTeachType newone=new CoachTeachType();
			BeanCopy.copyNotNull(oldone, newone);
			BeanCopy.copyNotNull(type, newone);
			if(subject!=null){
				StringBuilder builder=new StringBuilder();
				for(int i=0;i<subject.length;i++){
					builder.append(subject[i]).append(",");
				}
				if(builder.length()>0){
					builder.deleteCharAt(builder.length()-1);
				}
				newone.setSubject(builder.toString());
			}
			
			newone.setDblink(type.getDblink());
			teachTypeMapper.updateTeachType(newone);
		}catch(Exception ex){
			logger.error(getMethodName()+" error:",ex);
			throw new BizException(ex);
		}
	}

	@Override
	public CoachTeachType getTeachType(CoachTeachType type) {
		try{
			return teachTypeMapper.getTeachType(type);
		}catch(Exception ex){
			logger.error(getMethodName()+" error:",ex);
			throw ex;
		}
	}

	@Override
	public void deleteTeachType(CoachTeachType type) {
		try{
			if(type.getIds()!=null&&!type.getIds().equals("")){
				String ids[]=type.getIds().split(",");
				for(String id:ids){
					CoachTeachType ctt=new CoachTeachType();
					ctt.setId(Integer.parseInt(id));
					ctt.setDblink(type.getDblink());
					teachTypeMapper.deleteTeachType(ctt);
				}
			}else{
				teachTypeMapper.deleteTeachType(type);
			}
		}catch(Exception ex){
			logger.error(getMethodName()+" error:",ex);
			throw ex;
		}
	}

	@Override
	public void updateTeachTypeStatus(CoachTeachType type)  {
		try{
			CoachTeachType oldone= teachTypeMapper.getTeachType(type);
			CoachTeachType newone=new CoachTeachType();
			BeanCopy.copyNotNull(oldone, newone);
			BeanCopy.copyNotNull(type, newone);
			
			
			teachTypeMapper.updateTeachTypeStatus(newone);
		}catch(Exception ex){
			logger.error(getMethodName()+" error:",ex);
			throw new BizException(ex);
		}
	}

	@Override
	public List<CoachTeachType> listTeachType(CoachTeachType type) {
		try{
			List<CoachTeachType> types=teachTypeMapper.listTeachType(type);
			User param=new User();
			param.setSchoolid(type.getSchoolid());
			List<User> users= userService.selectSchoolUser(param);
			for(CoachTeachType t:types){
				for(User user:users){
					if(t.getUserId().intValue()==user.getId().intValue()){
						t.setCuser(user.getUsername());
					}
				}
			}
			return types;
		}catch(Exception ex){
			logger.error(getMethodName()+" error:",ex);
			throw ex;
		}
	}

	@Override
	public void addClassType(CoachClassType type) {
		
		try{
			classTypeMapper.addClassType(type);
			List<Classinfo> classinfo= type.getClassinfo();
			if(classinfo!=null&&classinfo.size()>0){
				for(Classinfo info:classinfo){
					info.setClasstypeid(type.getId());
				}
				
				Map params=new HashMap();
				params.put("list", classinfo);
				
				classinfoService.batchAddClass(params);
			}
		}catch(Exception ex){
			logger.error(getMethodName()+" error:",ex);
			throw ex;
		}
	}


	
	@Override
	public void updateClassType(CoachClassType type)  {
		try{
			CoachClassType oldone= classTypeMapper.getClassType(type);

			classTypeMapper.updateClassType(type);
			List<Classinfo> classinfo= type.getClassinfo();
			
			List<Classinfo> updates=new ArrayList();
			List<Classinfo> addes=new ArrayList();
			List<Classinfo> deles=new ArrayList();

			
			if(addes.size()>0){
				Map params=new HashMap();
				params.put("list", addes);
				classinfoService.batchAddClass(params);
			}
			if(deles.size()>0){
				Map params=new HashMap();
				params.put("list", deles);
				classinfoService.batchDelClass(params);
			}
			if(updates.size()>0){
				for(Classinfo u:  updates){
					classinfoService.updateClass(u);
				}
			}
		}catch(Exception ex){
			logger.error(getMethodName()+" error:",ex);
			throw ex;
		}
	}

	@Override
	public CoachClassType getClassType(CoachClassType type) {
		try{
			return classTypeMapper.getClassType(type);
		}catch(Exception ex){
			logger.error(getMethodName()+" error:",ex);
			throw ex;
		}
	}

	@Override
	public ResultBean deleteClassType(CoachClassType type) {
		ResultBean rb=null;
		try{
			if(type.getIds()!=null&&!type.getIds().equals("")){
				String ids[]=type.getIds().split(",");
				for(String id:ids){
					Classinfo cp=new Classinfo();
					cp.setDblink(type.getDblink());
					cp.setClasstypeid(Integer.parseInt(id));
					List<Classinfo> clss= classinfoService.selectAllList(cp);
					if(clss!=null&&clss.size()>0){
						rb=new ResultBean("班型已经被使用，不能删除");
						return rb;
					}
					
				}
				//
				for(String id:ids){
					CoachClassType cct=new CoachClassType();
					cct.setId(Integer.parseInt(id));
					classTypeMapper.deleteClassType(cct);
				}
			}else{
				classTypeMapper.deleteClassType(type);
				Classinfo classinfo=new Classinfo();
				classinfo.setClasstypeid(type.getId());
				classinfoService.deleteClassByType(classinfo);
			}
			
		}catch(Exception ex){
			logger.error(getMethodName()+" error:",ex);
			throw ex;
		}
		rb=new ResultBean();
		return rb;
	}

	@Override
	public void updateClassTypeStatus(CoachClassType type) {
		try{
			CoachClassType oldone= classTypeMapper.getClassType(type);
			CoachClassType newone=new CoachClassType();
			BeanCopy.copyNotNull(oldone, newone);
			BeanCopy.copyNotNull(type, newone);
			newone.setDblink(type.getDblink());
			classTypeMapper.updateClassTypeStatus(newone);
		}catch(Exception ex){
			logger.error(getMethodName()+" error:",ex);
			throw new BizException(ex);
		}
	}

	@Override
	public List<CoachClassType> listClassType(CoachClassType type) {
		try{
			User param=new User();
			param.setSchoolid(type.getSchoolid());
			List<User> users= userService.selectSchoolUser(param);
			
			PageUtil.startPage(type);
			List<CoachClassType> types= classTypeMapper.listClassType(type);
			for(CoachClassType t:types){
				for(User user:users){
					if(t.getUserId().intValue()==user.getId().intValue()){
						t.setCuser(user.getUsername());
					}
				}
			}
			return types;
		}catch(Exception ex){
			logger.error(getMethodName()+" error:",ex);
			throw ex;
		}
	}
	
	@Override
	public List<CoachClassType> listAllClassType(CoachClassType type) {
		try{
			User param=new User();
			param.setSchoolid(type.getSchoolid());
			List<User> users= userService.selectSchoolUser(param);
			
			List<CoachClassType> types= classTypeMapper.listClassType(type);
			for(CoachClassType t:types){
				for(User user:users){
					if(t.getUserId().intValue()==user.getId().intValue()){
						t.setCuser(user.getUsername());
					}
				}
			}
			return types;
		}catch(Exception ex){
			logger.error(getMethodName()+" error:",ex);
			throw ex;
		}
	}

	@Override
	public void addCarType(CoachCarType type) {
		try{
			carTypeMapper.addCarType(type);
		}catch(Exception ex){
			logger.error(getMethodName()+" error:",ex);
			throw ex;
		}
	}

	@Override
	public void deleteCarType(CoachCarType type) {
		try{
			carTypeMapper.deleteCarType(type);
		}catch(Exception ex){
			logger.error(getMethodName()+" error:",ex);
			throw ex;
		}
	}

	@Override
	public List<CoachCarType> listCarType(CoachCarType type) {
		try{
			return carTypeMapper.listCarType(type);
		}catch(Exception ex){
			logger.error(getMethodName()+" error:",ex);
			throw ex;
		}
	}

	@Override
	public void saveCarType(String[] newtypes,User user) {
		try{
			CoachCarType ctype=new CoachCarType();
			ctype.setDblink(user.getDblink());
			List<CoachCarType> all= carTypeMapper.listCarType(ctype);
			
			List<CoachCarType> addtypes=new ArrayList();
			List<CoachCarType> deltypes=new ArrayList();
			for(String ntype:newtypes){
				boolean flag=false;
				for(CoachCarType type:all){
					if(ntype.equals(type.getType())){
						flag=true;break;
					}
				}
				if(!flag){
					CoachCarType n=new CoachCarType();
					
					n.setUserId(user.getId());
					n.setType(ntype);
					addtypes.add(n);
				}
			}
			
			for(CoachCarType type:all){
				boolean flag=false;
				for(String ntype:newtypes){
					if(ntype.equals(type.getType())){
						flag=true;break;
					}
				}
				if(!flag){
					deltypes.add(type);
				}
			}
			
			if(addtypes.size()>0){
				Map params=new HashMap();
				params.put("list", addtypes);
				
				carTypeMapper.batchAddCarType(params);
			}
			if(deltypes.size()>0){
				Map params=new HashMap();
				params.put("list", deltypes);
				carTypeMapper.batchDelCarType(params);
			}
			
		}catch(Exception ex){
			logger.error(getMethodName()+" error:",ex);
			throw ex;
		}
	}

	@Override
	public void addJob(CoachJob job)  {
		try{
			jobMapper.addJob(job);
		}catch(Exception ex){
			logger.error(getMethodName()+" error:",ex);
			throw ex;
		}
	}

	@Override
	public void updateJob(CoachJob job)  {
		try{
			CoachJob oldone= jobMapper.getJob(job);
			CoachJob newone=new CoachJob();
			BeanCopy.copyNotNull(oldone, newone);
			BeanCopy.copyNotNull(job, newone);
			
			jobMapper.updateJob( newone);
		}catch(Exception ex){
			logger.error(getMethodName()+" error:",ex);
			throw new RuntimeException(ex);
		}
		
	}

	@Override
	public CoachJob getJob(CoachJob param)  {
		try{
			return jobMapper.getJob(param);
		}catch(Exception ex){
			logger.error(getMethodName()+" error:",ex);
			throw ex;
		}
	}

	@Override
	public void deleteJob(CoachJob job)  {
		
		try{
			if(job.getIds()!=null&&!job.getIds().equals("")){
				String ids[]=job.getIds().split(",");
				for(String id:ids){
					CoachJob param=new CoachJob();
					param.setId(Integer.parseInt(id));
					jobMapper.deleteJob(param);
				}
			}else{
				jobMapper.deleteJob(job);
			}
			
		}catch(Exception ex){
			logger.error(getMethodName()+" error:",ex);
			throw ex;
		}
	}

	@Override
	public List<CoachJob> listJob(CoachJob job)  {
		try{
			PageUtil.startPage(job);
			List<CoachJob> jobs= jobMapper.listJob(job);
			User param=new User();
			param.setSchoolid(job.getSchoolid());
			List<User> users= userService.selectSchoolUser(param);
			for(CoachJob t:jobs){
				for(User user:users){
					if(t.getUserId().intValue()==user.getId().intValue()){
						t.setCuser(user.getUsername());
					}
				}
			}
			return jobs;
		}catch(Exception ex){
			logger.error(getMethodName()+" error:",ex);
			throw ex;
		}
	}
	
	@Override
	public List<CoachJob> listAllJob(CoachJob job)  {
		try{
			
			List<CoachJob> jobs= jobMapper.listJob(job);
			User param=new User();
			param.setSchoolid(job.getSchoolid());
			List<User> users= userService.selectSchoolUser(param);
			for(CoachJob t:jobs){
				for(User user:users){
					if(t.getUserId().intValue()==user.getId().intValue()){
						t.setCuser(user.getUsername());
					}
				}
			}
			return jobs;
		}catch(Exception ex){
			logger.error(getMethodName()+" error:",ex);
			throw ex;
		}
	}

	@Override
	public void updateJobStatus(CoachJob job)  {
		jobMapper.updateJobStatus(job);
		
	}
	
	  
}

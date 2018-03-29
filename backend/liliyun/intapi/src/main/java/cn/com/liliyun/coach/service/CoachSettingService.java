package cn.com.liliyun.coach.service;

import java.util.List;

import cn.com.liliyun.coach.model.CoachCarType;
import cn.com.liliyun.coach.model.CoachClassType;
import cn.com.liliyun.coach.model.CoachJob;
import cn.com.liliyun.coach.model.CoachTeachType;
import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.log.model.LogCommon;
import cn.com.liliyun.user.model.User;

public interface CoachSettingService {
	
	/**
	 * 带教类型
	 * @param type
	 */
	public void addTeachType(CoachTeachType type,String[] subject)  ;
	
	public void updateTeachType(CoachTeachType type,String[] subject)  ;
	
	public CoachTeachType getTeachType(CoachTeachType type);
	
	public void deleteTeachType(CoachTeachType type) ;
	
	public void updateTeachTypeStatus(CoachTeachType type)  ;
	
	public List<CoachTeachType> listTeachType(CoachTeachType type) ;
	
	/**
	 * 带教班级
	 * @param type
	 */
	public void addClassType(CoachClassType type)  ;
	
	public void updateClassType(CoachClassType type)  ;
	
	public CoachClassType getClassType(CoachClassType type) ;
	
	public ResultBean deleteClassType(CoachClassType type)  ;
	
	public void updateClassTypeStatus(CoachClassType type)  ;
	
	public List<CoachClassType> listClassType(CoachClassType type)  ;
	
	public List<CoachClassType> listAllClassType(CoachClassType type);
	
	/**
	 * 车型
	 * @param type
	 */
	public void addCarType(CoachCarType type)  ;
	
	public void deleteCarType(CoachCarType type)  ;
	
	public List<CoachCarType> listCarType(CoachCarType type)  ;
	
	public void saveCarType(String[] newtypes,User user) ;
	
	/**
	 * 职务
	 * @param type
	 * @param log
	 * @
	 */
	public void addJob(CoachJob job)  ;
	
	public void updateJob(CoachJob job)  ;
	
	public CoachJob getJob(CoachJob param)  ;
	
	public void deleteJob(CoachJob job)  ;
	
	public void updateJobStatus(CoachJob job)  ;
	
	public List<CoachJob> listJob(CoachJob job)  ;
	
	public List<CoachJob> listAllJob(CoachJob job) ;
}

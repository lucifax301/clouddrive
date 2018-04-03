package cn.com.liliyun.coach.service;

import java.util.List;

import cn.com.liliyun.coach.model.CoachCarType;
import cn.com.liliyun.coach.model.CoachClassType;
import cn.com.liliyun.coach.model.CoachJob;
import cn.com.liliyun.coach.model.CoachTeachType;
import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.user.model.User;

public interface CoachSettingService {
	
	/**
	 * 带教类型
	 * @param type
	 */
	void addTeachType(CoachTeachType type,String[] subject)  ;
	
	void updateTeachType(CoachTeachType type,String[] subject)  ;
	
	CoachTeachType getTeachType(CoachTeachType type);
	
	void deleteTeachType(CoachTeachType type) ;
	
	void updateTeachTypeStatus(CoachTeachType type)  ;
	
	List<CoachTeachType> listTeachType(CoachTeachType type) ;
	
	/**
	 * 带教班级
	 * @param type
	 */
	void addClassType(CoachClassType type)  ;
	
	void updateClassType(CoachClassType type)  ;
	
	CoachClassType getClassType(CoachClassType type) ;
	
	ResultBean deleteClassType(CoachClassType type)  ;
	
	void updateClassTypeStatus(CoachClassType type)  ;
	
	List<CoachClassType> listClassType(CoachClassType type)  ;
	
	List<CoachClassType> listAllClassType(CoachClassType type);
	
	/**
	 * 车型
	 * @param type
	 */
	void addCarType(CoachCarType type)  ;
	
	void deleteCarType(CoachCarType type)  ;
	
	List<CoachCarType> listCarType(CoachCarType type)  ;
	
	void saveCarType(String[] newtypes,User user) ;
	
	/**
	 * 职务
	 * @param type
	 * @param log
	 * @
	 */
	void addJob(CoachJob job)  ;
	
	void updateJob(CoachJob job)  ;
	
	CoachJob getJob(CoachJob param)  ;
	
	void deleteJob(CoachJob job)  ;
	
	void updateJobStatus(CoachJob job)  ;
	
	List<CoachJob> listJob(CoachJob job)  ;
	
	List<CoachJob> listAllJob(CoachJob job) ;
}

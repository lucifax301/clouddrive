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
	public void addTeachType(CoachTeachType type,String[] subject,LogCommon log) throws Exception ;
	
	public void updateTeachType(CoachTeachType type,String[] subject) throws Exception ;
	
	public CoachTeachType getTeachType(CoachTeachType type) throws Exception ;
	
	public void deleteTeachType(CoachTeachType type) throws Exception ;
	
	public void updateTeachTypeStatus(CoachTeachType type) throws Exception ;
	
	public List<CoachTeachType> listTeachType(CoachTeachType type) throws Exception ;
	
	/**
	 * 带教班级
	 * @param type
	 */
	public void addClassType(CoachClassType type,LogCommon log) throws Exception ;
	
	public void updateClassType(CoachClassType type) throws Exception ;
	
	public CoachClassType getClassType(CoachClassType type) throws Exception ;
	
	public ResultBean deleteClassType(CoachClassType type) throws Exception ;
	
	public void updateClassTypeStatus(CoachClassType type) throws Exception ;
	
	public List<CoachClassType> listClassType(CoachClassType type) throws Exception ;
	
	public List<CoachClassType> listAllClassType(CoachClassType type);
	
	/**
	 * 车型
	 * @param type
	 */
	public void addCarType(CoachCarType type,LogCommon log) throws Exception ;
	
	public void deleteCarType(CoachCarType type) throws Exception ;
	
	public List<CoachCarType> listCarType(CoachCarType type) throws Exception ;
	
	public void saveCarType(String[] newtypes,User user) throws Exception;
	
	/**
	 * 职务
	 * @param type
	 * @param log
	 * @throws Exception
	 */
	public void addJob(CoachJob job,LogCommon log) throws Exception ;
	
	public void updateJob(CoachJob job) throws Exception ;
	
	public CoachJob getJob(CoachJob param) throws Exception ;
	
	public void deleteJob(CoachJob job) throws Exception ;
	
	public void updateJobStatus(CoachJob job) throws Exception ;
	
	public List<CoachJob> listJob(CoachJob job) throws Exception ;
	
	public List<CoachJob> listAllJob(CoachJob job) throws Exception;
}

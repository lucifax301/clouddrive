package cn.com.liliyun.coach.manager;

import java.util.List;

import cn.com.liliyun.coach.model.CoachCarType;
import cn.com.liliyun.coach.model.CoachClassType;
import cn.com.liliyun.coach.model.CoachJob;
import cn.com.liliyun.coach.model.CoachTeachType;
import cn.com.liliyun.user.model.User;

@Deprecated
public interface CoachSettingManager {

	/**
	 * 带教类型
	 * @param type
	 */
	public void addTeachType(CoachTeachType type);
	
	public void updateTeachType(CoachTeachType oldone,CoachTeachType newone);
	
	public CoachTeachType getTeachType(int id);
	
	public void deleteTeachType(int id);
	
	public void updateTeachTypeStatus(CoachTeachType oldtype,CoachTeachType newtype);
	
	public List<CoachTeachType> listTeachType();
	
	/**
	 * 带教班级
	 * @param type
	 */
	public void addClassType(CoachClassType type);
	
	public void updateClassType(CoachClassType oldtype,CoachClassType newtype);
	
	public CoachClassType getClassType(int id);
	
	public void deleteClassType(int id);
	
	public void updateClassTypeStatus(CoachClassType type);
	
	public List<CoachClassType> listClassType();
	
	/**
	 * 车型
	 * @param type
	 */
	public void addCarType(CoachCarType type);
	
	public void deleteCarType(String type);
	
	public List<CoachCarType> listCarType();
	
	public void saveCarType(List<CoachCarType> addtypes,List<CoachCarType> deltypes,User user);
	
	/**
	 * 职务
	 * @param type
	 */
	public void addJob(CoachJob job);
	
	public void updateJob(CoachJob oldtype,CoachJob newtype);
	
	public CoachJob getJob(int id);
	
	public void deleteJob(int id);
	
	public List<CoachJob> listJob();
}

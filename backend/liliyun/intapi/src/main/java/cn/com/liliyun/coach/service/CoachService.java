package cn.com.liliyun.coach.service;

import java.util.List;
import java.util.Map;

import cn.com.liliyun.coach.model.Coach;
import cn.com.liliyun.coach.model.CoachClassinfo;
import cn.com.liliyun.coach.model.CoachLoadStudentInfo;
import cn.com.liliyun.coach.model.CoachModApply;
import cn.com.liliyun.coach.model.CoachModApplyParam;
import cn.com.liliyun.coach.model.CoachStudentDTO;
import cn.com.liliyun.coach.model.HeadCoach;
import cn.com.liliyun.coach.model.StudentAssign;
import cn.com.liliyun.common.model.ResultBean;


public interface CoachService {
	
	public ResultBean addCoach(Coach coach,Map extendsinfo);
	
	public List<Coach> getCoachList(Coach coach);
	
	public List<Coach> getExportCoachList(Coach coach);
	
	public List<Coach> getNoAssignCoachList(Coach coach);
	
	public List<HeadCoach> getHeadCoachList(Coach coach);
	
	public Map getCoachExtendById(Coach coach);
	
	/**
	 * 更新教练车
	 * @param coachid
	 * @param carno
	 * @return
	 */
	//public ResultBean updateCoachCar(int coachid,String carno,User user);
	/**
	 * 增加教练负荷学员数
	 * @param coachid
	 * @return
	 */
	public ResultBean incrementCoachStudent(int coachid);
	/**
	 * 减少教练负荷学员数
	 * @param coachid
	 * @return
	 */
	public ResultBean decrementCoachStudent(int coachid);
	
	public ResultBean updateCoach(Coach coach,Map extendsinfo);
	
	public ResultBean updateCoachTeachState(Coach coach);
	
	public ResultBean updateCoachEmploystatus(Coach coach);
	
	public void deleteById(Coach coach);
	
	public Coach getCoachById(Coach coach);
	
	public ResultBean getCoachModinfo(Coach coach,int applyid);
	
	public void importCoach(Coach coach,List<Coach> list);
	
	public Integer getCount(Coach coach);
	
	public ResultBean getNotStuListOfCoach(CoachStudentDTO CoachStudentDTO);
	
	public ResultBean getStuListOfCoach(CoachStudentDTO CoachStudentDTO);
	
	public ResultBean getStuAssignRecord(StudentAssign studentAssign) ;
	
	public List<StudentAssign> getAllStuAssignRecord(StudentAssign studentAssign) ;
	
	/**
	 * 教练信息修改申请
	 * @param coach
	 * @param log
	 * @param extendsinfo
	 * @param user
	 * @param businessid
	 * @return
	 */
	public ResultBean modCoachApply(Coach coach,Map extendsinfo,String businessid);
	
	public CoachModApply getModApply(CoachModApply param);
	
	public ResultBean updateModCoachApply(Coach coach,Map extendsinfo,int applyid);
	
	public ResultBean listModCoachApply(CoachModApplyParam param);
	
	public ResultBean auditModCoachApply(int applyid,int state);
	
	public ResultBean batchAuditModCoachApply(String[] applyid,int state);
	
	public ResultBean assignCoach(int headcoachid,String coachid[],String delcoachid[]);
	
	public ResultBean batchUpdateCoach(String coachid[],Coach coach ,
			 String classinfoid[]);
	
	public Map<Integer, CoachClassinfo> selectCoachClassBatch(List<Integer> ids);
	
	public List<CoachLoadStudentInfo> getCoachLoadStudentInfo(CoachLoadStudentInfo info);
	
	public Map getCoachModExtendinfo(Coach coach,int applyid);
	
	public Map<Integer, Integer> getStoreCoachNumBatch(List<Integer> storeids);
	
	public ResultBean assignList(Coach coach, Integer studentid);
}

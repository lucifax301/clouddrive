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
	
	ResultBean addCoach(Coach coach,Map extendsinfo);
	
	List<Coach> getCoachList(Coach coach);
	
	List<Coach> getExportCoachList(Coach coach);
	
	List<Coach> getNoAssignCoachList(Coach coach);
	
	List<HeadCoach> getHeadCoachList(Coach coach);
	
	Map<String,String> getCoachExtendById(Coach coach);
	
	/**
	 * 更新教练车
	 * @param coachid
	 * @param carno
	 * @return
	 */
	//ResultBean updateCoachCar(int coachid,String carno,User user);
	/**
	 * 增加教练负荷学员数
	 * @param coachid
	 * @return
	 */
	ResultBean incrementCoachStudent(int coachid);
	/**
	 * 减少教练负荷学员数
	 * @param coachid
	 * @return
	 */
	ResultBean decrementCoachStudent(int coachid);
	
	ResultBean updateCoach(Coach coach,Map extendsinfo);
	
	ResultBean updateCoachTeachState(Coach coach);
	
	ResultBean updateCoachEmploystatus(Coach coach);
	
	void deleteById(Coach coach);
	
	Coach getCoachById(Coach coach);
	
	ResultBean getCoachModinfo(Coach coach,int applyid);
	
	void importCoach(Coach coach,List<Coach> list);
	
	Integer getCount(Coach coach);
	
	ResultBean getNotStuListOfCoach(CoachStudentDTO CoachStudentDTO);
	
	ResultBean getStuListOfCoach(CoachStudentDTO CoachStudentDTO);
	
	ResultBean getStuAssignRecord(StudentAssign studentAssign) ;
	
	List<StudentAssign> getAllStuAssignRecord(StudentAssign studentAssign) ;
	
	/**
	 * 教练信息修改申请
	 * @param coach
	 * @param log
	 * @param extendsinfo
	 * @param user
	 * @param businessid
	 * @return
	 */
	ResultBean modCoachApply(Coach coach,Map extendsinfo);
	
	CoachModApply getModApply(CoachModApply param);
	
	ResultBean updateModCoachApply(Coach coach,Map extendsinfo,int applyid);
	
	ResultBean listModCoachApply(CoachModApplyParam param);
	
	ResultBean auditModCoachApply(int applyid,int state);
	
	ResultBean batchAuditModCoachApply(String[] applyid,int state);
	
	ResultBean assignCoach(int headcoachid,String coachid[],String delcoachid[]);
	
	ResultBean batchUpdateCoach(String coachid[],Coach coach ,
			 String classinfoid[]);
	
	Map<Integer, CoachClassinfo> selectCoachClassBatch(List<Integer> ids);
	
	List<CoachLoadStudentInfo> getCoachLoadStudentInfo(CoachLoadStudentInfo info);
	
	Map<String,String> getCoachModExtendinfo(Coach coach,int applyid);
	
	Map<Integer, Integer> getStoreCoachNumBatch(List<Integer> storeids);
	
	ResultBean assignList(Coach coach, Integer studentid);
}

package cn.com.liliyun.student.service;


import java.util.List;
import java.util.Map;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.student.dto.*;
import cn.com.liliyun.student.model.CoachStudent;
import cn.com.liliyun.student.model.Student;
import cn.com.liliyun.student.model.StudentMoney;
import cn.com.liliyun.student.model.StudentPauseApply;
import cn.com.liliyun.student.model.StudentPauseApplyParam;
import cn.com.liliyun.student.model.StudentStatusLog;
import cn.com.liliyun.student.model.TransferStudent;
import cn.com.liliyun.theory.dto.TheoryLessonStoreDto;
import cn.com.liliyun.theory.dto.TheoryStudentExport;
import cn.com.liliyun.theory.model.TheoryLesson;
import cn.com.liliyun.theory.model.TheoryStudent;
import cn.com.liliyun.user.model.User;

public interface StudentService {
	
	public ResultBean addStudent(User user,Student student);
	
	public ResultBean deleteStudent(Student student);
	
	public ResultBean updateStudentWithCheck(Student student);
	
	public ResultBean doUpdateStudentAfterCheck(Student student);
	
	public ResultBean updateStudent(User user,Student student);
	
	public ResultBean checkStudent(User user,Student student);
	
	public Student getStudent(Student student);
	
	public ResultBean getStudentList(Student student, User user);
	
	public ResultBean getEnrolStudentList(Student student);
	
	public List <Student> getList(Student student);
	
	public List <Student> getAllList(Student student);
	
	public Integer getClassStudentOne(Student student);

	public Integer getCount(Student student);

	public List<CountDTO> get7count(Student student);

    public Map<String, Object> importFlownum(User user,Map<String, Object> params);

	public ResultBean getTheoryList(TheoryLesson theoryLesson, User user);
	
	public ResultBean getTheory(TheoryLessonStoreDto theoryLesson, User user, boolean isReview);
	
	public ResultBean getTheoryStores(User user);
	
	public ResultBean getTheoryStudents(Student student, User user);
	
	public ResultBean addTheory(TheoryLesson theoryLesson, String stores, User user);
	
	public ResultBean editTheoryStudent(Integer theoryId, String[] ids, User user, boolean isDel);
	
	public ResultBean updateTheory(TheoryLesson theoryLesson, User user, String businessid);
	
	public ResultBean addCoachStudent(CoachStudent coachStudent, Boolean isreview, User user, String businessid);
	
	public CoachStudent getCoachStudent(CoachStudent coachStudent, User user);
	
	public ResultBean addStudentPauseApply(StudentPauseApply apply,User user,String bussinessid );
	
	public ResultBean updateStudentPauseApply(StudentPauseApply apply,User user);
	
	public ResultBean listStudentPauseApply(StudentPauseApplyParam param,User user);
	
	public ResultBean getStudentPauseApply(StudentPauseApply apply,User user);
	
	public ResultBean getStudentPauseApplyByTransaction(StudentPauseApply apply,User user);

	public ResultBean getStudentPauseApplyByStuId(StudentPauseApply apply);
	
	public String theoryLessonText(Integer theoryid, Integer type, User user);
	
	public ResultBean updateStudentPauseApplyStatus(StudentPauseApply apply,User user);
	
	public List<TheoryStudentExport> theoryStudentExport(TheoryStudent theoryStudent, User user);

	public List<Student> getCoachStudentList(CoachStudent coachStudent);

	public ResultBean getTransferList(TransferStudent transferStudent, Boolean isChosen, User user);
	
	public ResultBean getTransfer(TransferStudent transferStudent, User user);
	
	public ResultBean addTransfer(TransferStudent transferStudent, User user, String businessid);
	
	public ResultBean editTransfer(TransferStudent transferStudent, User user);
	
	public ResultBean getTStudentList(Student student, User user);

	public ResultBean getStudentCoach(StudentCoachDTO studetCoach, User user);

	public void saveStudentStatusLog(User user,StudentStatusLog statusLog);

	public void saveLogBatch(User user,List <StudentStatusLog> list);

	public void updateStudentBatch(User user,List <Student> list);

	public ResultBean getChangeLogList(StudentStatusLog statusLog);
	
	public StudentMoney getStudentMoney(StudentMoney studentMoney);

	public ResultBean updateStudentMoney(StudentMoney studentMoney);

	List <StudentMoneyDTO> selectOweList(User user,StudentMoneyDTO dto);

	public ResultBean calcMoney(User user, StudentCalcMoneyDTO dto);

	public List<StudentApplyStat> selectApplyStat(StudentApplyStat studentApplyStat, User user);

	public void updateReceiptStudentMoney(List<StudentMoney> list, String ids, User user);
}

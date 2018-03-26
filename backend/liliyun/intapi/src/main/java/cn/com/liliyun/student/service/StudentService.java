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


public interface StudentService {
	
	public ResultBean addStudent(Student student);
	
	public ResultBean deleteStudent(Student student);
	
	public ResultBean updateStudentWithCheck(Student student);
	
	public ResultBean doUpdateStudentAfterCheck(Student student);
	
	public ResultBean updateStudent(Student student);
	
	public ResultBean checkStudent(Student student);
	
	public Student getStudent(Student student);
	
	public ResultBean getStudentList(Student student);
	
	public ResultBean getEnrolStudentList(Student student);
	
	public List <Student> getList(Student student);
	
	public List <Student> getAllList(Student student);
	
	public Integer getClassStudentOne(Student student);

	public Integer getCount(Student student);

	public List<CountDTO> get7count(Student student);

    public Map<String, Object> importFlownum(Map<String, Object> params);

	public ResultBean getTheoryList(TheoryLesson theoryLesson);
	
	public ResultBean getTheory(TheoryLessonStoreDto theoryLesson, boolean isReview);
	
	public ResultBean getTheoryStores();
	
	public ResultBean getTheoryStudents(Student student);
	
	public ResultBean addTheory(TheoryLesson theoryLesson, String stores);
	
	public ResultBean editTheoryStudent(Integer theoryId, String[] ids, boolean isDel);
	
	public ResultBean updateTheory(TheoryLesson theoryLesson, String businessid);
	
	public ResultBean addCoachStudent(CoachStudent coachStudent, Boolean isreview, String businessid);
	
	public CoachStudent getCoachStudent(CoachStudent coachStudent);
	
	public ResultBean addStudentPauseApply(StudentPauseApply apply,String bussinessid );
	
	public ResultBean updateStudentPauseApply(StudentPauseApply apply);
	
	public ResultBean listStudentPauseApply(StudentPauseApplyParam param);
	
	public ResultBean getStudentPauseApply(StudentPauseApply apply);
	
	public ResultBean getStudentPauseApplyByTransaction(StudentPauseApply apply);

	public ResultBean getStudentPauseApplyByStuId(StudentPauseApply apply);
	
	public String theoryLessonText(Integer theoryid, Integer type);
	
	public ResultBean updateStudentPauseApplyStatus(StudentPauseApply apply);
	
	public List<TheoryStudentExport> theoryStudentExport(TheoryStudent theoryStudent);

	public List<Student> getCoachStudentList(CoachStudent coachStudent);

	public ResultBean getTransferList(TransferStudent transferStudent, Boolean isChosen);
	
	public ResultBean getTransfer(TransferStudent transferStudent);
	
	public ResultBean addTransfer(TransferStudent transferStudent, String businessid);
	
	public ResultBean editTransfer(TransferStudent transferStudent);
	
	public ResultBean getTStudentList(Student student);

	public ResultBean getStudentCoach(StudentCoachDTO studetCoach);

	public void saveStudentStatusLog(StudentStatusLog statusLog);

	public void saveLogBatch(List <StudentStatusLog> list);

	public void updateStudentBatch(List <Student> list);

	public ResultBean getChangeLogList(StudentStatusLog statusLog);
	
	public StudentMoney getStudentMoney(StudentMoney studentMoney);

	public ResultBean updateStudentMoney(StudentMoney studentMoney);

	List <StudentMoneyDTO> selectOweList(StudentMoneyDTO dto);

	public ResultBean calcMoney(StudentCalcMoneyDTO dto);

	public List<StudentApplyStat> selectApplyStat(StudentApplyStat studentApplyStat);

	public void updateReceiptStudentMoney(List<StudentMoney> list, String ids);
}

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
	
	ResultBean addStudent(Student student);
	
	ResultBean deleteStudent(Student student);
	
	ResultBean updateStudentWithCheck(Student student);
	
	ResultBean doUpdateStudentAfterCheck(Student student);
	
	ResultBean updateStudent(Student student);
	
	ResultBean checkStudent(Student student);
	
	Student getStudent(Student student);
	
	ResultBean getStudentList(Student student);
	
	ResultBean getEnrolStudentList(Student student);
	
	List <Student> getList(Student student);
	
	List <Student> getAllList(Student student);
	
	Integer getClassStudentOne(Student student);

	Integer getCount(Student student);

	List<CountDTO> get7count(Student student);

    Map<String, Object> importFlownum(Map<String, Object> params);

	ResultBean getTheoryList(TheoryLesson theoryLesson);
	
	ResultBean getTheory(TheoryLessonStoreDto theoryLesson, boolean isReview);
	
	ResultBean getTheoryStores();
	
	ResultBean getTheoryStudents(Student student);
	
	ResultBean addTheory(TheoryLesson theoryLesson, String stores);
	
	ResultBean editTheoryStudent(Integer theoryId, String[] ids, boolean isDel);
	
	ResultBean updateTheory(TheoryLesson theoryLesson);
	
	ResultBean addCoachStudent(CoachStudent coachStudent, Boolean isreview);
	
	CoachStudent getCoachStudent(CoachStudent coachStudent);
	
	ResultBean addStudentPauseApply(StudentPauseApply apply);
	
	ResultBean updateStudentPauseApply(StudentPauseApply apply);
	
	ResultBean listStudentPauseApply(StudentPauseApplyParam param);
	
	ResultBean getStudentPauseApply(StudentPauseApply apply);
	
	ResultBean getStudentPauseApplyByTransaction(StudentPauseApply apply);

	ResultBean getStudentPauseApplyByStuId(StudentPauseApply apply);
	
	String theoryLessonText(Integer theoryid, Integer type);
	
	ResultBean updateStudentPauseApplyStatus(StudentPauseApply apply);
	
	List<TheoryStudentExport> theoryStudentExport(TheoryStudent theoryStudent);

	List<Student> getCoachStudentList(CoachStudent coachStudent);

	ResultBean getTransferList(TransferStudent transferStudent, Boolean isChosen);
	
	ResultBean getTransfer(TransferStudent transferStudent);
	
	ResultBean addTransfer(TransferStudent transferStudent);
	
	ResultBean editTransfer(TransferStudent transferStudent);
	
	ResultBean getTStudentList(Student student);

	ResultBean getStudentCoach(StudentCoachDTO studetCoach);

	void saveStudentStatusLog(StudentStatusLog statusLog);

	void saveLogBatch(List <StudentStatusLog> list);

	void updateStudentBatch(List <Student> list);

	ResultBean getChangeLogList(StudentStatusLog statusLog);
	
	StudentMoney getStudentMoney(StudentMoney studentMoney);

	ResultBean updateStudentMoney(StudentMoney studentMoney);

	List <StudentMoneyDTO> selectOweList(StudentMoneyDTO dto);

	ResultBean calcMoney(StudentCalcMoneyDTO dto);

	List<StudentApplyStat> selectApplyStat(StudentApplyStat studentApplyStat);

	void updateReceiptStudentMoney(List<StudentMoney> list, String ids);
}

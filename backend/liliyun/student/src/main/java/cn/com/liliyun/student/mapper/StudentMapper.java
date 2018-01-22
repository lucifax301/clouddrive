package cn.com.liliyun.student.mapper;

import java.util.List;
import java.util.Map;

import cn.com.liliyun.student.dto.CountDTO;
import cn.com.liliyun.student.dto.StudentApplyStat;
import cn.com.liliyun.student.model.CoachStudent;
import cn.com.liliyun.student.model.Student;
import cn.com.liliyun.theory.dto.TheoryStudentExport;
import cn.com.liliyun.theory.model.TheoryStudent;

public interface StudentMapper {

	int deleteByPrimaryKey(Student record);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Student record);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);
    
    List<Student> selectList(Student student);
    
    List<Student> selectEnrolList(Student student);
    
    Integer getClassStudentCount(Student student);
    
    List<Student> selectCoachList(CoachStudent coachStudent);
    
    Student selectOne(Student student);
	
	//in条件查询学员, Id�? 1,2,3, 格式输入到stunum传�??
	public List<Student> selectStudentInIds(Student student);
	
	/*
	 * 
	 */
	public List<TheoryStudentExport> selectStudentExport(TheoryStudent theoryStudent);
	
	/*
	 * 通过theoryId�? storeId�? areaId获取可�?�择的学员列�?
	 */
	List<Student> selectTheoryStudents(Student student);
	
	int updateLearnstatus(Student record);
	
	int updateBatch(Map <String,Object> params);
	
	List<StudentApplyStat> selectApplyStat(StudentApplyStat studentApplyStat);

    public List<CountDTO> getCountBy7Days (Student student);

    public int getCount(Student student);

}
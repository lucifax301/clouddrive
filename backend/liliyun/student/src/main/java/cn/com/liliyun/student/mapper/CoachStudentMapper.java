package cn.com.liliyun.student.mapper;

import java.util.List;
import java.util.Map;

import cn.com.liliyun.coach.model.CoachClassinfo;
import cn.com.liliyun.student.dto.StudentCoachDTO;
import cn.com.liliyun.student.model.CoachStudent;

public interface CoachStudentMapper {

	public void insert(CoachStudent coachStudent);
	
	public int count(CoachStudent coachStudent);
	
	public List<CoachStudent> list(CoachStudent coachStudent);
	
	public CoachStudent get(CoachStudent coachStudent);
	
	public void update(CoachStudent coachStudent);
	
	public void insertBatch(Map data);
	
	public void delete(CoachStudent coachStudent);
	
	public void updateSelective(CoachStudent coachStudent);
	
	public List<StudentCoachDTO> selectStudentCoach(StudentCoachDTO studentCoachDTOs);

}

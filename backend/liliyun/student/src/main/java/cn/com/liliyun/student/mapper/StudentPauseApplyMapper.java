package cn.com.liliyun.student.mapper;

import java.util.List;
import java.util.Map;

import cn.com.liliyun.coach.model.CoachModApply;
import cn.com.liliyun.student.model.CoachStudent;
import cn.com.liliyun.student.model.StudentPauseApply;
import cn.com.liliyun.student.model.StudentPauseApplyParam;

public interface StudentPauseApplyMapper {

	public void insert(StudentPauseApply apply);
	
	public int count(StudentPauseApplyParam param);
	
	public List<StudentPauseApply> list(StudentPauseApplyParam param);
	
	public StudentPauseApply get(StudentPauseApply apply);
	
	public StudentPauseApply getByTransaction(StudentPauseApply apply);
	
	
	public StudentPauseApply getByStudentId(StudentPauseApply apply);
	
	
	public void update(StudentPauseApply apply);
	
	public void updateStatus(StudentPauseApply apply);
	
	public void delete(StudentPauseApply apply);
	
	public StudentPauseApply getApplyByStudentid(StudentPauseApply apply);
}

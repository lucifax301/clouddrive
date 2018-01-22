package cn.com.liliyun.coach.mapper;

import java.util.List;

import cn.com.liliyun.coach.model.CoachTeachType;

public interface TeachTypeMapper {

	public void addTeachType(CoachTeachType type);
	
	public void updateTeachType(CoachTeachType type);
	
	public CoachTeachType getTeachType(CoachTeachType type);
	
	public void deleteTeachType(CoachTeachType type);
	
	public void updateTeachTypeStatus(CoachTeachType type);
	
	public List<CoachTeachType> listTeachType(CoachTeachType type);
}

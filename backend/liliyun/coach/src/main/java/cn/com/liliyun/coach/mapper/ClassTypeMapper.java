package cn.com.liliyun.coach.mapper;

import java.util.List;

import cn.com.liliyun.coach.model.CoachClassType;

public interface ClassTypeMapper {

	public void addClassType(CoachClassType type);
	
	public void updateClassType(CoachClassType type);
	
	public CoachClassType getClassType(CoachClassType type);
	
	public void deleteClassType(CoachClassType type);
	
	public void updateClassTypeStatus(CoachClassType type);
	
	public List<CoachClassType> listClassType(CoachClassType type);
}

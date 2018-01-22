package cn.com.liliyun.coach.mapper;

import java.util.List;
import java.util.Map;

import cn.com.liliyun.coach.model.CoachCarType;

public interface CarTypeMapper {

	public void addCarType(CoachCarType type);
	
	public void deleteCarType(CoachCarType type);
	
	public List<CoachCarType> listCarType(CoachCarType type);
	
	public void batchAddCarType(Map data);
	
	public void batchDelCarType(Map data);
}

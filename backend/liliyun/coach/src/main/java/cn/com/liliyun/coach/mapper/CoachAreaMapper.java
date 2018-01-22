package cn.com.liliyun.coach.mapper;

import java.util.List;
import java.util.Map;

import cn.com.liliyun.coach.model.CoachArea;

public interface CoachAreaMapper {

	public void add(CoachArea info);
	
	public void batchAdd(Map map);
	
	public void batchDel(Map map);
	
	public List<CoachArea> list(CoachArea info);
}

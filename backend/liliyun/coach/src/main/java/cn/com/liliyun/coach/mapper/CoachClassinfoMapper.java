package cn.com.liliyun.coach.mapper;

import java.util.List;
import java.util.Map;

import cn.com.liliyun.coach.model.CoachClassinfo;

public interface CoachClassinfoMapper {

	public void add(CoachClassinfo info);
	
	public void batchAdd(Map map);
	
	public void batchDel(Map map);
	
	public List<CoachClassinfo> list(CoachClassinfo info);
	
	public List<CoachClassinfo> selectCoachClassBatch(Map map);
}

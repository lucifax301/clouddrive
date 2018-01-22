package cn.com.liliyun.market.mapper;

import java.util.List;

import cn.com.liliyun.market.model.CoachPerformanceStatItem;
import cn.com.liliyun.market.model.PerformanceParam;
import cn.com.liliyun.market.model.StaffPerformanceStatItem;



public interface PerformanceStatMapper {

	public List<CoachPerformanceStatItem> statByCoach(PerformanceParam param);
	
	
	public List<StaffPerformanceStatItem> statByStaff(PerformanceParam param);
	
	
}

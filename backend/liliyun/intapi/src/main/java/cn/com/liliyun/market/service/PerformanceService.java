package cn.com.liliyun.market.service;

import java.util.List;

import cn.com.liliyun.market.model.CoachPerformanceStat;
import cn.com.liliyun.market.model.PerformanceParam;
import cn.com.liliyun.market.model.StaffPerformanceStat;

public interface PerformanceService {

	List<CoachPerformanceStat> coachstat(PerformanceParam param);
	
	List<StaffPerformanceStat> staffstat(PerformanceParam param);
}

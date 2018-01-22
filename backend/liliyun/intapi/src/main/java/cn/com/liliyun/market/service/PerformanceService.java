package cn.com.liliyun.market.service;

import java.util.List;

import cn.com.liliyun.market.model.CoachPerformanceStat;
import cn.com.liliyun.market.model.PerformanceParam;
import cn.com.liliyun.market.model.StaffPerformanceStat;
import cn.com.liliyun.user.model.User;

public interface PerformanceService {

	public List<CoachPerformanceStat> coachstat(PerformanceParam param,User user);
	
	public List<StaffPerformanceStat> staffstat(PerformanceParam param,User user);
}

package cn.com.liliyun.coach.service;

import java.util.List;

import cn.com.liliyun.coach.model.stat.AreaStat;
import cn.com.liliyun.coach.model.stat.AreaStatParam;
import cn.com.liliyun.coach.model.stat.CoachAreaStat;
import cn.com.liliyun.coach.model.stat.CoachStatParam;
import cn.com.liliyun.coach.model.stat.HeadCoachAreaStat;
import cn.com.liliyun.coach.model.stat.HeadCoachStatParam;
import cn.com.liliyun.coach.model.stat.TeachTypeStat;
import cn.com.liliyun.user.model.User;

public interface CoachStatService {

	public List<AreaStat> statByArea(AreaStatParam param) throws Exception;
	
	public List<TeachTypeStat> statByTeachType(AreaStatParam param) throws Exception;
	
	public List<CoachAreaStat> statByCoach(CoachStatParam param, User user) throws Exception ;
	
	public List<HeadCoachAreaStat> statByHeadCoach(HeadCoachStatParam param, User user) throws Exception ;
}

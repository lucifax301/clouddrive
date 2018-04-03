package cn.com.liliyun.coach.service;

import java.util.List;

import cn.com.liliyun.coach.model.stat.AreaStat;
import cn.com.liliyun.coach.model.stat.AreaStatParam;
import cn.com.liliyun.coach.model.stat.CoachAreaStat;
import cn.com.liliyun.coach.model.stat.CoachStatParam;
import cn.com.liliyun.coach.model.stat.HeadCoachAreaStat;
import cn.com.liliyun.coach.model.stat.HeadCoachStatParam;
import cn.com.liliyun.coach.model.stat.TeachTypeStat;

public interface CoachStatService {

	List<AreaStat> statByArea(AreaStatParam param) ;
	
	List<TeachTypeStat> statByTeachType(AreaStatParam param) ;
	
	List<CoachAreaStat> statByCoach(CoachStatParam param)  ;
	
	List<HeadCoachAreaStat> statByHeadCoach(HeadCoachStatParam param);
}

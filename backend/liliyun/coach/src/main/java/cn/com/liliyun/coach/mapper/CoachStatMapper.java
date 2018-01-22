package cn.com.liliyun.coach.mapper;

import java.util.List;

import cn.com.liliyun.coach.model.stat.AreaStatParam;
import cn.com.liliyun.coach.model.stat.AreaStatRecord;
import cn.com.liliyun.coach.model.stat.CoachStatParam;
import cn.com.liliyun.coach.model.stat.CoachStatRecord;
import cn.com.liliyun.coach.model.stat.HeadCoachStatParam;
import cn.com.liliyun.coach.model.stat.HeadCoachStatRecord;

public interface CoachStatMapper {

	public List<AreaStatRecord> statByArea(AreaStatParam param);
	
	public List<AreaStatRecord> statByTeachType(AreaStatParam param);
	
	public List<CoachStatRecord> statByCoach(CoachStatParam param);
	
	public List<HeadCoachStatRecord> statByHeadCoach(HeadCoachStatParam param);
}

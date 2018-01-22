package cn.com.liliyun.market.mapper;

import java.util.List;

import cn.com.liliyun.market.model.CoachEnrolIndex;

public interface CoachEnrolIndexMapper {

	public void addCoachEnrolIndex(CoachEnrolIndex index);
	
	public List<CoachEnrolIndex> listCoachEnrolIndex(CoachEnrolIndex param);
	
	public CoachEnrolIndex getCoachEnrolIndex(CoachEnrolIndex index);
	
	public void updateCoachEnrolIndex(CoachEnrolIndex index);
	
	
	
	
}

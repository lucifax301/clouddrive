package cn.com.liliyun.coach.mapper;

import java.util.List;

import cn.com.liliyun.coach.model.Coach;
import cn.com.liliyun.coach.model.HeadCoach;

public interface HeadCoachMapper {

	public void addHeadCoach(HeadCoach headCoach);
	
	public void delHeadCoach(HeadCoach headCoach);
	
	public List<HeadCoach> listHeadCoach(Coach coach);
	
	public List<HeadCoach> statHeadCoach(HeadCoach headCoach);
	
	
	public void updateHeadCoach(HeadCoach headCoach);
	
	public void updateHeadCoachData(HeadCoach headCoach);
	
}

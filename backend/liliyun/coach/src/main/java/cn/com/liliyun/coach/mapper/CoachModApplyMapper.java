package cn.com.liliyun.coach.mapper;

import java.util.List;

import cn.com.liliyun.coach.model.CoachClassType;
import cn.com.liliyun.coach.model.CoachModApply;
import cn.com.liliyun.coach.model.CoachModApplyParam;

public interface CoachModApplyMapper {

	public void addApply(CoachModApply apply);
	
	public void updateApply(CoachModApply apply);
	
	public void updateApplyStatus(CoachModApply apply);
	
	public List<CoachModApply> listApply(CoachModApplyParam param);
	
	public CoachModApply getApply(CoachModApply apply);
	
	public CoachModApply getApplyByTransaction(CoachModApply apply);
	
	public CoachModApply getApplyByCoachid(CoachModApply apply);
}

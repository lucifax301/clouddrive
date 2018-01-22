package cn.com.liliyun.coach.mapper;

import java.util.List;

import cn.com.liliyun.coach.model.CoachClassType;
import cn.com.liliyun.coach.model.CoachJob;

public interface JobMapper {

	public void addJob(CoachJob job);
	
	public void updateJob(CoachJob job);
	
	public CoachJob getJob(CoachJob job);
	
	public void deleteJob(CoachJob job);
	
	public List<CoachJob> listJob(CoachJob job);
	
	public void updateJobStatus(CoachJob job);
}

package cn.com.liliyun.flow.mapper;

import java.util.List;

import cn.com.liliyun.flow.model.Flow;
import cn.com.liliyun.flow.model.FlowStep;

public interface FlowStepMapper {
    
	public void add(FlowStep flowStep);
	
	public void update(FlowStep flowStep);
	
	public FlowStep get(FlowStep flowStep);
	
	public FlowStep getById(FlowStep flowStep);
	
	public FlowStep getLast(FlowStep flowStep);
	
	public FlowStep getLastByTransactionid(Flow flow);
	
	public List<FlowStep> getByFlowId(FlowStep flowStep);
}
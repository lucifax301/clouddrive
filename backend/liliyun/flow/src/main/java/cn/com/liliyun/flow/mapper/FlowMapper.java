package cn.com.liliyun.flow.mapper;

import java.util.List;

import cn.com.liliyun.flow.model.Flow;
import cn.com.liliyun.flow.model.FlowStep;

public interface FlowMapper {
    
	public void addFlow(Flow flow);
	
	public List<Flow> listFlow(Flow flow);
	
	public Flow get(Flow flow);
	
	public Flow getByTransaction(Flow flow);
	
	public void updateFlowStep(Flow flow);
	
	public void updateFlowStatus(Flow flow);
	
	public List<Flow> listUserFlow(FlowStep flowstep);
	
	public List<Flow> listMyFlow(Flow flow);
	
	public Integer countTemplateFlow(Flow flow);
}
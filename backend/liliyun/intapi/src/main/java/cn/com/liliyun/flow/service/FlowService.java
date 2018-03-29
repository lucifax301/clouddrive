package cn.com.liliyun.flow.service;

import java.util.List;

import cn.com.liliyun.flow.model.Flow;
import cn.com.liliyun.flow.model.FlowStep;
import cn.com.liliyun.user.model.User;

public interface FlowService {
	
	/**
	 * 发起事务流，返回事务ID
	 * @param businessid
	 * @param userid
	 * @return
	 */
	public String addFlow(String businessid,int userid,String desc);
	
	public boolean auditFlow(Flow flow,int userid,int audit);
	
	public FlowStep getLastFlowStep(Flow flow);
	
	public FlowStep getLastFlowStepByTransactionid(Flow flow);
	
	public List<FlowStep> listFlowStep(FlowStep flowStep);
	
	/**
	 * 根据事务ID获取事务流
	 * @param transactionid
	 * @return
	 */
	public Flow getFlow(String transactionid);
	
	public List<Flow> selectFlow(Flow flow);
	
	public List<Flow> findUserFlow(FlowStep flowStep);
	
	public List<Flow> findMyFlow(Flow flow);
}


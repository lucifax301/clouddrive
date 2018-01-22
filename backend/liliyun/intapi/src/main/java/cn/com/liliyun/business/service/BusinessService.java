package cn.com.liliyun.business.service;

import java.util.List;

import cn.com.liliyun.business.model.ActionBusiness;
import cn.com.liliyun.business.model.Business;
import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.flow.model.Flow;
import cn.com.liliyun.flow.model.FlowStep;
import cn.com.liliyun.flow.model.FlowTemplate;
import cn.com.liliyun.user.model.User;

public interface BusinessService {

	public ResultBean addBusiness(Business business);
	
	public List<Business> listBusiness(Business business);
	
	public ResultBean delBusiness(Business business);
	
	public ResultBean updateBusiness(Business business);
	
	
	
	public ResultBean addActionBusiness(ActionBusiness actionBusiness);
	
	public ResultBean updateActionBusiness(ActionBusiness actionBusiness);
	
	public ActionBusiness getActionBusiness(ActionBusiness actionBusiness);
	
	public List<ActionBusiness> listActionBusiness(ActionBusiness actionBusiness);
	
	public ResultBean delActionBusiness(ActionBusiness actionBusiness);
	
	
	public ResultBean addFlowTemplate(FlowTemplate flowTemplate);
	
	public ResultBean delFlowTemplate(FlowTemplate flowTemplate);
	
	public List<FlowTemplate> listFlowTemplate(FlowTemplate flowTemplate);
	
	public FlowTemplate getFlowTemplate(FlowTemplate flowTemplate);
	
	public ResultBean updateFlowTemplate(FlowTemplate flowTemplate);
	
	public List<Flow> findUserFlow(FlowStep flowStep,User user);
	
	public List<Flow> findMyFlow(Flow flow,User user);
	
	public List<FlowStep> listFlowStep(FlowStep flowStep);
}

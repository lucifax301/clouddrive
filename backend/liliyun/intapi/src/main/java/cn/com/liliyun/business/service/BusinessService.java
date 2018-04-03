package cn.com.liliyun.business.service;

import java.util.List;

import cn.com.liliyun.business.model.ActionBusiness;
import cn.com.liliyun.business.model.Business;
import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.flow.model.Flow;
import cn.com.liliyun.flow.model.FlowStep;
import cn.com.liliyun.flow.model.FlowTemplate;

public interface BusinessService {

	ResultBean addBusiness(Business business);
	
	List<Business> listBusiness(Business business);
	
	ResultBean delBusiness(Business business);
	
	ResultBean updateBusiness(Business business);
	
	
	
	ResultBean addActionBusiness(ActionBusiness actionBusiness);
	
	ResultBean updateActionBusiness(ActionBusiness actionBusiness);
	
	ActionBusiness getActionBusiness(ActionBusiness actionBusiness);
	
	List<ActionBusiness> listActionBusiness(ActionBusiness actionBusiness);
	
	ResultBean delActionBusiness(ActionBusiness actionBusiness);
	
	
	ResultBean addFlowTemplate(FlowTemplate flowTemplate);
	
	ResultBean delFlowTemplate(FlowTemplate flowTemplate);
	
	List<FlowTemplate> listFlowTemplate(FlowTemplate flowTemplate);
	
	FlowTemplate getFlowTemplate(FlowTemplate flowTemplate);
	
	ResultBean updateFlowTemplate(FlowTemplate flowTemplate);
	
	List<Flow> findUserFlow(FlowStep flowStep);
	
	List<Flow> findMyFlow(Flow flow);
	
	List<FlowStep> listFlowStep(FlowStep flowStep);
}

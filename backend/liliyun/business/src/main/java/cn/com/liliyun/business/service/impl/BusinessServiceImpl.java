package cn.com.liliyun.business.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.liliyun.business.mapper.ActionBusinessMapper;
import cn.com.liliyun.business.mapper.BusinessMapper;
import cn.com.liliyun.business.model.ActionBusiness;
import cn.com.liliyun.business.model.Business;
import cn.com.liliyun.business.service.BusinessService;
import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.common.util.PageUtil;
import cn.com.liliyun.flow.mapper.FlowMapper;
import cn.com.liliyun.flow.mapper.FlowTemplateMapper;
import cn.com.liliyun.flow.model.Flow;
import cn.com.liliyun.flow.model.FlowStep;
import cn.com.liliyun.flow.model.FlowTemplate;
import cn.com.liliyun.flow.service.FlowService;
import cn.com.liliyun.user.model.User;

@Service
public class BusinessServiceImpl implements BusinessService {

	@Autowired
	private BusinessMapper businessMapper;
	@Autowired
	private ActionBusinessMapper actionBusinessMapper;
	@Autowired
	private FlowTemplateMapper flowTemplateMapper;
	@Autowired
	private FlowMapper flowMapper;
	
	@Autowired
	private FlowService flowService;
	
	@Override
	public ResultBean addBusiness(Business business) {
		Business exist= businessMapper.getByBusinessid(business);
		if(exist!=null){
			ResultBean rb=new ResultBean("业务编码已经存在");
			return rb;
		}
		businessMapper.add(business);
		return new ResultBean();
	}

	@Override
	public List<Business> listBusiness(Business business) {
		return businessMapper.list(business);
		
	}

	@Override
	public ResultBean delBusiness(Business business) {
		
		String [] ids = business.getIds().split(",");
		for (String id : ids) {
			business.setId(Integer.parseInt(id));
			businessMapper.delete(business);
		}
		return new ResultBean();
	}

	@Override
	public ResultBean updateBusiness(Business business) {
		Business exist= businessMapper.getByBusinessid(business);
		if(exist!=null&&exist.getId().intValue()!=business.getId().intValue()){
			ResultBean rb=new ResultBean("业务编码已经被其他业务使用");
			return rb;
		}
		businessMapper.update(business);
		return new ResultBean();
	}

	@Override
	public ResultBean addActionBusiness(ActionBusiness actionBusiness) {
		ActionBusiness exist= actionBusinessMapper.getByAction(actionBusiness);
		if(exist!=null){
			ResultBean rb=new ResultBean("业务URI已经存在");
			return rb;
		}
		actionBusinessMapper.add(actionBusiness);
		return new ResultBean();
	}
	
	@Override
	public ResultBean updateActionBusiness(ActionBusiness actionBusiness) {
		actionBusinessMapper.update(actionBusiness);
		return new ResultBean();
	}
	
	@Override
	public ResultBean delActionBusiness(ActionBusiness actionBusiness) {
		
		String [] ids = actionBusiness.getIds().split(",");
		for (String id : ids) {
			actionBusiness.setId(Integer.parseInt(id));
			actionBusinessMapper.delete(actionBusiness);
		}
		return new ResultBean();
	}

	@Override
	public ActionBusiness getActionBusiness(ActionBusiness actionBusiness) {
		return actionBusinessMapper.getByAction(actionBusiness);
	}
	
	@Override
	public List<ActionBusiness> listActionBusiness(ActionBusiness actionBusiness) {
		return actionBusinessMapper.list(actionBusiness);
	}

	@Override
	public ResultBean addFlowTemplate(FlowTemplate flowTemplate) {
		
		FlowTemplate exist= flowTemplateMapper.getByBusiness(flowTemplate);
		if(exist!=null){
			ResultBean rb= new ResultBean("此业务已经有审批流");
			return rb;
		}
		flowTemplateMapper.add(flowTemplate);;
		return new ResultBean();
	}

	@Override
	public ResultBean delFlowTemplate(FlowTemplate flowTemplate) {
		Flow flow=new Flow();
		if(flowTemplate.getIds()!=null&&flowTemplate.getIds().length()>0){
			String[] ids=flowTemplate.getIds().split(",");
			for(int i=0;i<ids.length;i++){
				flow.setTemplateid(Integer.parseInt(ids[i]));
				Integer count= flowMapper.countTemplateFlow(flow);
				if(count!=null&&count>0){
					ResultBean rb= new ResultBean("此业务已经被使用，不能删除");
					return rb;
				}
			}
			
			for(int i=0;i<ids.length;i++){
				flowTemplate.setId(Integer.parseInt(ids[i]));
				flowTemplateMapper.delete(flowTemplate);
			}
		}else{
			flow.setTemplateid(flowTemplate.getId());
			Integer count= flowMapper.countTemplateFlow(flow);
			if(count!=null&&count>0){
				ResultBean rb= new ResultBean("此业务已经被使用，不能删除");
				return rb;
			}
			flowTemplateMapper.delete(flowTemplate);
		}
		return new ResultBean();
	}

	@Override
	public List<FlowTemplate> listFlowTemplate(FlowTemplate flowTemplate) {
		PageUtil.startPage(flowTemplate);
		return flowTemplateMapper.list(flowTemplate);
	}

	@Override
	public FlowTemplate getFlowTemplate(FlowTemplate flowTemplate) {
		return flowTemplateMapper.get(flowTemplate);
	}

	@Override
	public ResultBean updateFlowTemplate(FlowTemplate flowTemplate) {
		flowTemplateMapper.update(flowTemplate);
		return new ResultBean();
	}

	@Override
	public List<Flow> findUserFlow(FlowStep flowStep) {
		return flowService.findUserFlow(flowStep);
	}

	@Override
	public List<Flow> findMyFlow(Flow flow) {
		return flowService.findMyFlow(flow);
	}

	@Override
	public List<FlowStep> listFlowStep(FlowStep flowStep) {
		return flowService.listFlowStep(flowStep);
	}
	
	
}

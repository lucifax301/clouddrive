package cn.com.liliyun.flow.service.impl;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.liliyun.common.model.RequestContext;
import cn.com.liliyun.common.util.ConstantUtil;
import cn.com.liliyun.common.util.PageUtil;
import cn.com.liliyun.common.util.StringUtil;
import cn.com.liliyun.flow.mapper.FlowMapper;
import cn.com.liliyun.flow.mapper.FlowStepMapper;
import cn.com.liliyun.flow.mapper.FlowTemplateMapper;
import cn.com.liliyun.flow.model.Flow;
import cn.com.liliyun.flow.model.FlowStep;
import cn.com.liliyun.flow.model.FlowTemplate;
import cn.com.liliyun.flow.service.FlowService;
import cn.com.liliyun.user.model.User;
import cn.com.liliyun.user.service.UserService;

@Service
public class FlowServiceImpl implements FlowService {
	
	
	@Autowired
	private FlowMapper flowMapper;
	
	@Autowired
	private FlowTemplateMapper flowTemplateMapper;
	
	@Autowired
	private FlowStepMapper flowStepMapper;
	
	@Autowired
	//private UserMapper userMapper;
	private UserService userService;
	
	@Override
	public Flow getFlow(String transactionid){
		Flow flow=new Flow();
		
		flow.setTransactionid(transactionid);
		return flowMapper.getByTransaction(flow);
	}
	
	@Override
	public FlowStep getLastFlowStep(Flow flow){
		FlowStep step=new FlowStep();
		step.setFlowid(flow.getId());
		step.setDblink(flow.getDblink());
		FlowStep curFlowStep= flowStepMapper.getLast(step);
		return curFlowStep;
	}
	
	@Override
	public FlowStep getLastFlowStepByTransactionid(Flow flow){
		FlowStep curFlowStep= flowStepMapper.getLastByTransactionid(flow);
		return curFlowStep;
	}
	
	private ConcurrentHashMap<String,String> jobmap=new ConcurrentHashMap();
	
	@Override
	public List<FlowStep> listFlowStep(FlowStep flowStep){
		List<User> users=userService.selectSchoolUser(new User());
		List<FlowStep> list= flowStepMapper.getByFlowId(flowStep);
		for(FlowStep step:list){
			for(User user:users){
				if(step.getUserid()==user.getId()){
					step.setUser(user.getRealname());
				}
			}
		}
		return list;
	}
	
	/*
	 * 获取用户待办或者已经办事项
	 */
	@Override
	public List<Flow> findUserFlow(FlowStep flowStep){
		PageUtil.startPage(flowStep);
		User user = RequestContext.getValue(ConstantUtil.USER_SESSION);
		flowStep.setUserid(user.getId());
		return flowMapper.listUserFlow(flowStep);
	}
	
	/**
	 * 获取用户创建的事项
	 * @param flowStep
	 * @param user
	 * @return
	 */
	@Override
	public List<Flow> findMyFlow(Flow flow){
		PageUtil.startPage(flow);
		User user = RequestContext.getValue(ConstantUtil.USER_SESSION);
		flow.setFuserid(user.getId());
		return flowMapper.listMyFlow(flow);
	}
	
	/**
	 * 审核流转到下一个用户
	 * @param flow
	 * @param userid
	 * @param audit 1 审核通过 2 审核不通过 3取消
	 * @return true 标识还有下一个审核，false 标识没有
	 */
	@Override
	public boolean auditFlow(Flow flow,int userid,int audit){
		
		String businessid=flow.getBusinessid();
		FlowTemplate flowTemplateParam=new FlowTemplate();
		flowTemplateParam.setBusinessid(businessid);
		flowTemplateParam.setDblink(flow.getDblink());
		FlowTemplate flowTemplate= flowTemplateMapper.getByBusiness(flowTemplateParam);
		User curuser=userService.getUser(new User(userid));//userMapper.selectByPrimaryKey(new User(userid));
		
		int curroleid=curuser.getRoleid();
		String rule=flowTemplate.getRule();
		Integer nextruleid=getNextRole(rule,curroleid);
		/*
		 * @todo
		 * 1.还有有下一个要处理的角色，却找不到用户的异常异常情况要处理
		 * 2.并发同时修改业务流的状态要处理
		 */
		if(audit==1){
			if(nextruleid!=null){//有下一个要审核人
				User nextuser=null;
				User param=new User();
				param.setRoleid(nextruleid);
				param.setDblink(curuser.getDblink());
				
				List<User> roleuser=userService.selectRoleUser(param);
				nextuser=getNextUser(roleuser,curuser);
				if(nextuser!=null){
					FlowStep step=new FlowStep();
					step.setFlowid(flow.getId());
					step.setDblink(flow.getDblink());
					FlowStep curFlowStep= flowStepMapper.getLast(step);
					
					flow.setCuruserid(nextuser.getId());
					flow.setStep(curFlowStep.getStep()+1);
					flowMapper.updateFlowStep(flow);
					
					curFlowStep.setStatus(audit);
					flowStepMapper.update(curFlowStep);
					
					FlowStep nextflowstep=new FlowStep();
					nextflowstep.setDblink(flow.getDblink());
					nextflowstep.setFlowid(flow.getId());
					nextflowstep.setUserid(nextuser.getId());
					nextflowstep.setStep(curFlowStep.getStep()+1);
					flowStepMapper.add(nextflowstep);
					return true;
				}else{//找不到下一个用户，虽然有角色，却没有角色对应用户,目前策略是不审核
					FlowStep step=new FlowStep();
					step.setFlowid(flow.getId());
					step.setDblink(flow.getDblink());
					
					FlowStep curFlowStep= flowStepMapper.getLast(step);
					curFlowStep.setStatus(audit);
					curFlowStep.setDblink(flow.getDblink());
					flowStepMapper.update(curFlowStep);
					
					flow.setStatus(audit);
					flowMapper.updateFlowStatus(flow);
					
					return false;
				}
			}else{//没有下一个
				FlowStep step=new FlowStep();
				step.setFlowid(flow.getId());
				step.setDblink(flow.getDblink());
				
				FlowStep curFlowStep= flowStepMapper.getLast(step);
				curFlowStep.setStatus(audit);
				curFlowStep.setDblink(flow.getDblink());
				flowStepMapper.update(curFlowStep);
				
				flow.setStatus(audit);
				flowMapper.updateFlowStatus(flow);
				
				return false;
			}
		}else if(audit==2){//审核不通过
			FlowStep step=new FlowStep();
			step.setFlowid(flow.getId());
			step.setDblink(flow.getDblink());
			
			FlowStep curFlowStep= flowStepMapper.getLast(step);
			curFlowStep.setStatus(audit);
			curFlowStep.setDblink(flow.getDblink());
			flowStepMapper.update(curFlowStep);
			flow.setStatus(audit);
			flowMapper.updateFlowStatus(flow);
			return false;
		}else if(audit==3){//取消
			FlowStep step=new FlowStep();
			step.setFlowid(flow.getId());
			step.setDblink(flow.getDblink());
			
			FlowStep curFlowStep= flowStepMapper.getLast(step);
			curFlowStep.setStatus(audit);
			curFlowStep.setDblink(flow.getDblink());
			flowStepMapper.update(curFlowStep);
			
			flow.setStatus(audit);
			flowMapper.updateFlowStatus(flow);
			return false;
		}
		return false;
	}
	
	private User getNextUser(List<User> roleuser,User curuser){
		User nextuser=null;
		for(User u:roleuser){
			System.out.println("============"+u.getAreaid()+" "+curuser.getAreaid());
			if(curuser.getStoreid()!=null){//门店人员
				if(u.getStoreid()!=null){//下一个角色是门店
					if(u.getStoreid().intValue()==curuser.getStoreid().intValue()){
						nextuser=u;break;
					}
				}else if(u.getAreaid()!=null){//下一个角色是片区
					if(u.getAreaid().intValue()==curuser.getAreaid().intValue()){
						nextuser=u;break;
					}
				}else{//下一个角色是总店
					nextuser=u;break;
				}
			}else if(curuser.getAreaid()!=null){//片区人员
				if(u.getAreaid()!=null){//下一个角色是片区
					if(u.getAreaid().intValue()==curuser.getAreaid().intValue()){
						nextuser=u;break;
					}
				}else if(u.getAreaid()==null&&(u.getStoreid()==null||u.getStoreid()==0)){
					nextuser=u;break;
				}
			}else{//总店人员
				if(u.getAreaid()==null&&(u.getStoreid()==null||u.getStoreid()==0)){//下一个角色是总店
					nextuser=u;break;
				}
			}
		}
		return nextuser;
	}
	
	@Override
	public String addFlow(String businessid,int userid,String desc) {
		if(businessid==null){
			return StringUtil.getUUID();
		}
		FlowTemplate flowTemplateParam=new FlowTemplate();
		
		flowTemplateParam.setBusinessid(businessid);
		FlowTemplate flowTemplate= flowTemplateMapper.getByBusiness(flowTemplateParam);
		if(flowTemplate!=null){
			//User curuser=userMapper.selectByPrimaryKey(new User(userid));
			User up=new User(userid);
			up.setMgrdb(true);
			User curuser=userService.getUser(up);
			
			//int curroleid=curuser.getRoleid();
			String rule=flowTemplate.getRule();
			Integer nextruleid=getNextRole(rule,0);
			if(nextruleid!=null){
				User param=new User();
				param.setRoleid(nextruleid);
				param.setDblink(curuser.getDblink());
				param.setMgrdb(true);
				User nextuser=null;
				List<User> roleuser=userService.selectRoleUser(param);//userMapper.selectRoleUser(param);
				nextuser=getNextUser(roleuser,curuser);
				if(nextuser!=null){
					String transactionid=StringUtil.getUUID();
					Flow flow=new Flow();
					flow.setBusinessid(businessid);
					flow.setFuserid(userid);
					flow.setCuruserid(nextuser.getId());
					flow.setDescription(desc);
					flow.setTemplateid(flowTemplate.getId());
					flow.setTransactionid(transactionid);
					
					flowMapper.addFlow(flow);
					
					FlowStep flowstep=new FlowStep();
					flowstep.setFlowid(flow.getId());
					flowstep.setStep(1);
					flowstep.setUserid(nextuser.getId());
					
					flowStepMapper.add(flowstep);
					
					return transactionid;
				}
			}
		}
		// TODO Auto-generated method stub
		return StringUtil.getUUID();	
	}

	/**
	 * 返回null表示没有下一个处理人
	 * @param rule
	 * @param curroleid
	 * @return
	 */
	private Integer getNextRole(String rule,int curroleid){
		String rules[]=rule.split(",");
		if(rule==null)
			return null;
		if(rules.length==0)
			return null;
		
		if(curroleid==0){//业务发起人传0, 因为rule里只有审批节点的角色id, 不包括发起者角色id
			return Integer.parseInt(rules[0]);
		}
		
		for(int i=0;i<rules.length;i++){
			if(Integer.parseInt(rules[i])==curroleid&&i<rules.length-1){
				return Integer.parseInt(rules[i+1]);
			}
		}
		return null;
	}

	@Override
	public List<Flow> selectFlow(Flow flow) {
		PageUtil.startPage(flow);
		List<Flow> list= flowMapper.listFlow(flow);
		return list;
	}
	
	
}

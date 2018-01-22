package cn.com.liliyun.market.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import cn.com.liliyun.coach.model.CoachModApply;
import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.common.model.ResultCode;
import cn.com.liliyun.common.util.ConstantUtil;
import cn.com.liliyun.common.util.PageUtil;
import cn.com.liliyun.flow.model.Flow;
import cn.com.liliyun.flow.model.FlowStep;
import cn.com.liliyun.flow.service.FlowService;
import cn.com.liliyun.log.model.LogCommon;
import cn.com.liliyun.market.mapper.MarketMapper;
import cn.com.liliyun.market.model.MarketActivity;
import cn.com.liliyun.market.service.MarketService;
import cn.com.liliyun.trainorg.model.Area;
import cn.com.liliyun.trainorg.model.Store;
import cn.com.liliyun.trainorg.service.AreaService;
import cn.com.liliyun.trainorg.service.StoreService;
import cn.com.liliyun.user.model.User;

@Service
public class MarketServiceImpl implements MarketService {

	@Autowired
	private MarketMapper marketMapper;
	
	@Autowired
	private FlowService flowService;
	
	@Autowired
	private StoreService storeService;
	
	@Autowired
	private AreaService areaService;
	
	@Override
	public ResultBean addMarketActivity(MarketActivity activity,LogCommon log,User user,String businessid) {
		ResultBean rb = new ResultBean();
		String desc="市场活动["+activity.getActivityname()+"]申请";
		String transactionid= flowService.addFlow(businessid, user.getId(),desc,user);
		activity.setBusinessid(businessid);
		activity.setTransactionid(transactionid);
		activity.setApplyuser(user.getUsername());
		activity.setApplyuserid(user.getId());
		marketMapper.addMarket(activity);
		return rb;
	}

	@Override
	public List<MarketActivity> listActivity(MarketActivity activity,User user) {
		if (activity.getEnddate() != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(activity.getEnddate());
			calendar.add(Calendar.DAY_OF_YEAR, 1);
			calendar.add(Calendar.SECOND, -1);
			activity.setEnddate(calendar.getTime());
		}
		
		PageUtil.startPage(activity);
		List<MarketActivity> list=marketMapper.listMarket(activity);
		
		for(MarketActivity apply:list){
			if(apply.getApplyuserid()==user.getId()){
				apply.setModapplystat(ConstantUtil.AUDIT_RIGHT_CAN_CANCEL);
			}
		}
		return list;
	}
	
	
	@Override
	public List<MarketActivity> listExportActivity(MarketActivity activity,User user) {
		List<MarketActivity> list= marketMapper.listMarket(activity);
		Area pa=new Area();
		pa.setDblink(user.getDblink());
		List<Area> areas= areaService.selectAllList(pa);
		
		Store ps=new Store();
		ps.setDblink(user.getDblink());
		List<Store> stores= storeService.selectAllList(ps, user);
		for(MarketActivity act:list){
			for(Area area:areas){
				if(act.getAreaid().intValue()==area.getId()){
					act.setArea(area.getName());break;
				}
			}
			for(Store store:stores){
				if(act.getStoreid().intValue()==store.getId()){
					act.setStore(store.getName());
				}
			}
			act.setDay(getDayPeriod(act.getBegindate(),act.getEnddate()));
			act.setPeriod(dateformat.format(act.getBegindate())+"~"+dateformat.format(act.getEnddate()));
			act.setTotalrent(act.getRent()*act.getDay());
			act.setStatusStr(getStatus(act.getStatus()));
		}
		return list;
	}
	
	private String getStatus(int status){
		if(status==0){
			return "待审核";
		}else if(status==1){
			return "审核通过";
		}else if(status==2){
			return "审核不通过";
		}else{
			return "已撤回";
		}
	}
	
	private java.text.SimpleDateFormat dateformat=new java.text.SimpleDateFormat("yyyy-MM-dd");
	
	private int getDayPeriod(Date begin,Date end){
		return (int)((end.getTime()-begin.getTime())/3600/1000/24)+1;
	}

	@Override
	public ResultBean updateMarketActivity(MarketActivity activity,LogCommon log,User user) {
		ResultBean rb = new ResultBean();
		MarketActivity apply=marketMapper.getMarket(activity);
		if(apply.getStatus()!=0){
			rb.setCode(ResultCode.ERRORCODE.APPLYHASAUDIT);
			rb.setMsg(ResultCode.ERRORINFO.APPLYHASAUDIT);
			return rb;
		}
		marketMapper.updateMarket(activity);
		return rb;
	}

	@Override
	public MarketActivity getMarketActivity(MarketActivity activity,User user) {
		MarketActivity apply= marketMapper.getMarket(activity);
		

		if (apply.getApplyuserid() == user.getId().intValue()) {// 当前用户是发起人
			if (apply.getStatus() == 0) {// 业务还在等待审核中
				apply.setModapplystat(ConstantUtil.AUDIT_RIGHT_CAN_CANCEL);
			} else {
				Flow flow = new Flow();
				flow.setDblink(user.getDblink());
				flow.setTransactionid(apply.getTransactionid());
				FlowStep fs = flowService.getLastFlowStepByTransactionid(flow);
				if(fs==null){//没有审批流，可以直接审核
					apply.setModapplystat(ConstantUtil.AUDIT_RIGHT_CAN_AUDIT);
				}else{
					apply.setModapplystat(ConstantUtil.AUDIT_RIGHT_NONE);
				}
			}
			Flow flow=flowService.getFlow(apply.getTransactionid(),user);
			apply.setExtend(flow);
		} else {
			Flow flow = new Flow();
			flow.setDblink(user.getDblink());
			flow.setTransactionid(apply.getTransactionid());
			FlowStep fs = flowService.getLastFlowStepByTransactionid(flow);
			if(fs==null){//没有审批流，可以直接审核
				apply.setModapplystat(ConstantUtil.AUDIT_RIGHT_CAN_AUDIT);
			}else{
				flow.setId(fs.getFlowid());
				apply.setExtend(flow);
				if (fs.getUserid() == user.getId()) {// 业务流到当前用户，可以审核
					apply.setModapplystat(ConstantUtil.AUDIT_RIGHT_CAN_AUDIT);
				} else {
					apply.setModapplystat(ConstantUtil.AUDIT_RIGHT_NONE);
				}
			}
		}
		
		return apply;
	}
	
	@Override
	public MarketActivity getMarketActivityByTran(MarketActivity activity,User user) {
		MarketActivity apply= marketMapper.getMarketByTran(activity);
		
		if (apply.getApplyuserid() == user.getId().intValue()) {// 当前用户是发起人
			if (apply.getStatus() == 0) {// 业务还在等待审核中
				apply.setModapplystat(ConstantUtil.AUDIT_RIGHT_CAN_CANCEL);
			} else {
				apply.setModapplystat(ConstantUtil.AUDIT_RIGHT_NONE);
			}
			Flow flow=flowService.getFlow(apply.getTransactionid(),user);
			apply.setExtend(flow);
		} else {
			Flow flow = new Flow();
			flow.setDblink(user.getDblink());
			flow.setTransactionid(apply.getTransactionid());
			FlowStep fs = flowService.getLastFlowStepByTransactionid(flow);
			if(fs==null){//没有审批流，可以直接审核
				apply.setModapplystat(ConstantUtil.AUDIT_RIGHT_CAN_AUDIT);
			}else{
				flow.setId(fs.getFlowid());
				apply.setExtend(flow);
				if (fs.getUserid() == user.getId()) {// 业务流到当前用户，可以审核
					apply.setModapplystat(ConstantUtil.AUDIT_RIGHT_CAN_AUDIT);
				} else {
					apply.setModapplystat(ConstantUtil.AUDIT_RIGHT_NONE);
				}
			}
		}
		
		return apply;
	}

	@Override
	public ResultBean auditMarketActivity(MarketActivity activity,LogCommon log,
			 User user) {
		ResultBean rb = new ResultBean();
		
		
		MarketActivity apply=marketMapper.getMarket(activity);
		Flow flow= flowService.getFlow(apply.getTransactionid(),user);
		if(apply.getStatus()==0){
			apply.setStatus(activity.getStatus());
			apply.setAudituserid(user.getId());
			apply.setAudituser(user.getUsername());
			apply.setAuditdate(new Date());
		}else{
			rb.setCode(ResultCode.ERRORCODE.APPLYHASAUDIT);
			rb.setMsg(ResultCode.ERRORINFO.APPLYHASAUDIT);
			return rb;
		}
		if(activity.getStatus()==1){
			flow.setDblink(user.getDblink());
			boolean next=(flow!=null)&&flowService.auditFlow(flow, user.getId(), ConstantUtil.AUDIT_ACCEPT);
			if(!next){
				
				marketMapper.updateMarketStatus(activity);
			}
		}else{
			marketMapper.updateMarketStatus(activity);
		}
		return rb;
	}

	@Override
	public ResultBean batchAuditMarketActivity(String[] applyid, int state,
			LogCommon log, User user) {
		ResultBean rb = new ResultBean();
		for(String id:applyid){
			MarketActivity activity=new MarketActivity(Integer.parseInt(id));
			activity.setStatus(state);
			
			MarketActivity apply=marketMapper.getMarket(activity);
			Flow flow= flowService.getFlow(apply.getTransactionid(),user);
			if(apply.getStatus()==0){
				apply.setStatus(activity.getStatus());
				apply.setAudituserid(user.getId());
				apply.setAudituser(user.getUsername());
				apply.setAuditdate(new Date());
			}else{
				continue;
			}
			if(activity.getStatus()==1){
				boolean next=(flow!=null)&&flowService.auditFlow(flow, user.getId(), ConstantUtil.AUDIT_ACCEPT);
				if(!next){
					
					marketMapper.updateMarketStatus(activity);
				}
			}else{
				marketMapper.updateMarketStatus(activity);
			}
		}
		return rb;
	}

	
}

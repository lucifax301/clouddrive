package cn.com.liliyun.market.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.liliyun.coach.model.CoachClassType;
import cn.com.liliyun.coach.service.CoachSettingService;
import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.common.util.PageUtil;
import cn.com.liliyun.flow.service.FlowService;
import cn.com.liliyun.log.model.LogCommon;
import cn.com.liliyun.market.mapper.SalesMapper;
import cn.com.liliyun.market.model.SalesActivity;
import cn.com.liliyun.market.model.SalesActivityClassinfo;
import cn.com.liliyun.market.service.SalesService;
import cn.com.liliyun.trainorg.model.Classinfo;
import cn.com.liliyun.trainorg.service.ClassinfoService;
import cn.com.liliyun.user.model.User;

@Service
public class SalesServiceImpl implements SalesService {

	private static Logger logger=Logger.getLogger(SalesServiceImpl.class);
	
	@Autowired
	private SalesMapper salesMapper;
	
	@Autowired
	private FlowService flowService;
	
	@Autowired
	private ClassinfoService classinfoService;
	
	@Autowired
	private CoachSettingService coachSettingService;

	@Override
	public ResultBean addSalesActivity(SalesActivity activity, LogCommon log,
			User user, String businessid) {
			
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(activity.getEnddate());
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		calendar.add(Calendar.SECOND, -1);
		activity.setEnddate(calendar.getTime());
		
		String transactionid= flowService.addFlow(businessid, user.getId(),"",user);
		activity.setTransactionid(transactionid);
		activity.setApplyuser(user.getUsername());
		activity.setApplyuserid(user.getId());
		salesMapper.addSales(activity);
		
		List<SalesActivityClassinfo> classinfo= activity.getClassinfo();
		if(classinfo!=null&&classinfo.size()>0){
			for(SalesActivityClassinfo info:classinfo){
				info.setActivityid(activity.getId());
				if(info.getC1price()==null)info.setC1price(0);
				if(info.getC2price()==null)info.setC2price(0);
			}
			
			Map params=new HashMap();
			params.put("list", classinfo);
			params.put("dblink", activity.getDblink());
			salesMapper.batchinsertClass(params);
		}
		
		return new ResultBean();
	}

	@Override
	public List<SalesActivity> listActivity(SalesActivity activity) {
		if (activity.getEnddate() != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(activity.getEnddate());
			calendar.add(Calendar.DAY_OF_YEAR, 1);
			calendar.add(Calendar.SECOND, -1);
			activity.setEnddate(calendar.getTime());
		}
		
		List<SalesActivity> list=null;
		/**
		 * 没有选择活动状态
		 */
		PageUtil.startPage(activity);
		if(activity.getSearchstatus()==null||activity.getSearchstatus().equals("")){
			list= salesMapper.listSales(activity);
		}else if(activity.getSearchstatus().equals("0")){//未开始
			list= salesMapper.listWaitSales(activity);
		}else if(activity.getSearchstatus().equals("1")){//进行中
			list= salesMapper.listRunningSales(activity);
		}else if(activity.getSearchstatus().equals("2")){//已结束
			list= salesMapper.listEndSales(activity);
		}else if(activity.getSearchstatus().equals("3")){//已关闭
			list= salesMapper.listCloseSales(activity);
		}
		List<SalesActivityClassinfo> scp=new ArrayList();
		Date now=new Date();
		for(SalesActivity act:list){
			SalesActivityClassinfo info=new SalesActivityClassinfo();
			info.setActivityid(act.getId());
			scp.add(info);
			act.setPeriod(dateformat.format(act.getBegindate())+"~"+dateformat.format(act.getEnddate()));
			
			act.setStatusStr(getStatus(act,now));
		}
		
		if(scp.size()>0){
			Map params=new HashMap();
			params.put("list", scp);
			params.put("dblink", activity.getDblink());
			List<Map> stat= salesMapper.statActivityStudent(params);
			for(SalesActivity act:list){
				for(Map s:stat){
					if(act.getId().intValue()==(Integer) s.get("activityid")){
						act.setEnrolcount(((Long)s.get("count")).intValue());
					}
				}
			}
		}
		return list;
	}
	
	private String getStatus(SalesActivity act,Date now){
		if(act.getActstatus()==1){
			return "已关闭";
		}else{
			if(now.after(act.getEnddate())){
				return "已结束";
			}else if(now.after(act.getBegindate())){
				return "进行中";
			}else if(now.before(act.getBegindate())){
				return "未开始";
			}else{
				return "";
			}
		}
	}
	
	private java.text.SimpleDateFormat dateformat=new java.text.SimpleDateFormat("yyyy-MM-dd");
	
	private int getDayPeriod(Date begin,Date end){
		return (int)((end.getTime()-begin.getTime())/3600/1000/24)+1;
	}
	
	@Override
	public List<SalesActivity> listMatchActivity(SalesActivityClassinfo activity) {
		List<SalesActivity> list= salesMapper.listMatchSales(activity);
		return list;
	}
	
	@Override
	public SalesActivityClassinfo getMatchActivityClass(SalesActivityClassinfo activity) {
		SalesActivityClassinfo one= salesMapper.getMatchClass(activity);
		return one;
	}

	@Override
	public ResultBean updateSalesActivity(SalesActivity activity,
			LogCommon log, User user) {
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(activity.getEnddate());
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		calendar.add(Calendar.SECOND, -1);
		activity.setEnddate(calendar.getTime());
		
		salesMapper.updateSales(activity);
		List<SalesActivityClassinfo> classinfo= activity.getClassinfo();
		SalesActivityClassinfo cf=new SalesActivityClassinfo();
		cf.setActivityid(activity.getId());
		cf.setDblink(user.getDblink());
		List<SalesActivityClassinfo> eclassinfo=salesMapper.getClass(cf);
		
		List<SalesActivityClassinfo> updates=new ArrayList();
		List<SalesActivityClassinfo> addes=new ArrayList();
		List<SalesActivityClassinfo> deles=new ArrayList();
		for(SalesActivityClassinfo e: eclassinfo){
			boolean find=false;
			for(SalesActivityClassinfo info:classinfo){
				
				if(info.getId()!=null&&e.getId().intValue()==info.getId().intValue()){
					find=true;
					if(info.getC1price()==null)info.setC1price(0);
					if(info.getC2price()==null)info.setC2price(0);
					updates.add(info);
				}
			}
			
			if(!find){
				deles.add(e);
			} 
		}
		
		for(SalesActivityClassinfo info:classinfo){
			if(info.getId()==null){
				addes.add(info);
				info.setActivityid(activity.getId());
			}
		}
		
		if(addes.size()>0){
			Map params=new HashMap();
			params.put("list", addes);
			params.put("dblink", activity.getDblink());
			salesMapper.batchinsertClass(params);
		}
		if(deles.size()>0){
			Map params=new HashMap();
			params.put("list", deles);
			params.put("dblink", activity.getDblink());
			salesMapper.batchdelClass(params);
		}
		if(updates.size()>0){
			for(SalesActivityClassinfo u:  updates){
				u.setDblink(activity.getDblink());
				salesMapper.updateClass(u);
			}
		}
		
		return new ResultBean();
	}

	@Override
	public SalesActivity getSalesActivity(SalesActivity activity)  {
		SalesActivity one= salesMapper.getSales(activity);
		if(one!=null){
			SalesActivityClassinfo cf=new SalesActivityClassinfo();
			cf.setActivityid(activity.getId());
			cf.setDblink(activity.getDblink());
			List<SalesActivityClassinfo> eclassinfo=salesMapper.getClass(cf);
			try{
				Classinfo ci=new Classinfo();
				ci.setDblink(activity.getDblink());
				List<Classinfo> clss= classinfoService.selectAllList(ci);
				
				CoachClassType cct=new CoachClassType();
				cct.setDblink(activity.getDblink());
				List<CoachClassType> list = coachSettingService.listClassType(cct);
			for(Classinfo cls:clss){
				boolean find=false;
				for(SalesActivityClassinfo info:eclassinfo){
					if(cls.getId().intValue()==info.getClassinfoid().intValue()){
						find=true;break;
					}
				}
				if(!find){
					SalesActivityClassinfo info=new SalesActivityClassinfo();
					info.setC1flag(cls.getC1flag());
					info.setC2flag(cls.getC2flag());
					info.setClassinfoid(cls.getId());
					info.setC1amount(cls.getC1amount());
					info.setC2amount(cls.getC2amount());
					info.setName(cls.getName());
					for(CoachClassType type:list){
						if(type.getId()==cls.getClasstypeid().intValue()){
							info.setClasstype(type.getType());
						}
					}
					eclassinfo.add(info);
				}
			}
			}catch(Exception ex){
				ex.printStackTrace();
			}
			one.setClassinfo(eclassinfo);
		}
		return one;
	}

	@Override
	public ResultBean auditSalesActivity(SalesActivity activity, LogCommon log,
			User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultBean batchSalesMarketActivity(String[] applyid, int state,
			LogCommon log, User user) {
		// TODO Auto-generated method stub
		return null;
	}

}

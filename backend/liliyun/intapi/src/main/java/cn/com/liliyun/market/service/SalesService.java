package cn.com.liliyun.market.service;

import java.util.List;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.log.model.LogCommon;
import cn.com.liliyun.market.model.SalesActivityClassinfo;
import cn.com.liliyun.market.model.SalesActivity;
import cn.com.liliyun.trainorg.model.Classinfo;
import cn.com.liliyun.user.model.User;

public interface SalesService {

	public ResultBean addSalesActivity(SalesActivity activity,LogCommon log,User user,String businessid);
	
	public List<SalesActivity> listActivity(SalesActivity activity);
	
	public List<SalesActivity> listMatchActivity(SalesActivityClassinfo activity);
	
	public ResultBean updateSalesActivity(SalesActivity activity,LogCommon log,User user);
	
	public SalesActivity getSalesActivity(SalesActivity activity);
	
	public ResultBean auditSalesActivity(SalesActivity activity,LogCommon log,
			 User user);
	
	public ResultBean batchSalesMarketActivity(String[] applyid,int state, LogCommon log,
			 User user);	
	
	public SalesActivityClassinfo getMatchActivityClass(SalesActivityClassinfo activity);
	
}

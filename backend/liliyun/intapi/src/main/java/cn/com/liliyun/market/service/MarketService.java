package cn.com.liliyun.market.service;

import java.util.List;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.log.model.LogCommon;
import cn.com.liliyun.market.model.MarketActivity;
import cn.com.liliyun.user.model.User;

public interface MarketService {

	public ResultBean addMarketActivity(MarketActivity activity,LogCommon log,User user,String businessid);
	
	public List<MarketActivity> listActivity(MarketActivity activity,User user);
	
	public List<MarketActivity> listExportActivity(MarketActivity activity,User user);
	
	public ResultBean updateMarketActivity(MarketActivity activity,LogCommon log,User user);
	
	public MarketActivity getMarketActivity(MarketActivity activity,User user);
	
	public ResultBean auditMarketActivity(MarketActivity activity,LogCommon log,
			 User user);
	
	public ResultBean batchAuditMarketActivity(String[] applyid,int state, LogCommon log,
			 User user);
	
	public MarketActivity getMarketActivityByTran(MarketActivity activity,User user);
}

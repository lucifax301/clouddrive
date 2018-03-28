package cn.com.liliyun.market.service;

import java.util.List;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.log.model.LogCommon;
import cn.com.liliyun.market.model.MarketActivity;
import cn.com.liliyun.user.model.User;

public interface MarketService {

	public ResultBean addMarketActivity(MarketActivity activity,String businessid);
	
	public List<MarketActivity> listActivity(MarketActivity activity);
	
	public List<MarketActivity> listExportActivity(MarketActivity activity);
	
	public ResultBean updateMarketActivity(MarketActivity activity);
	
	public MarketActivity getMarketActivity(MarketActivity activity);
	
	public ResultBean auditMarketActivity(MarketActivity activity);
	
	public ResultBean batchAuditMarketActivity(String[] applyid,int state);
	
	public MarketActivity getMarketActivityByTran(MarketActivity activity);
}

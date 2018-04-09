package cn.com.liliyun.market.service;

import java.util.List;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.market.model.MarketActivity;

public interface MarketService {

	ResultBean addMarketActivity(MarketActivity activity);
	
	List<MarketActivity> listActivity(MarketActivity activity);
	
	List<MarketActivity> listExportActivity(MarketActivity activity);
	
	ResultBean updateMarketActivity(MarketActivity activity);
	
	MarketActivity getMarketActivity(MarketActivity activity);
	
	ResultBean auditMarketActivity(MarketActivity activity);
	
	ResultBean batchAuditMarketActivity(String[] applyid,int state);
	
	MarketActivity getMarketActivityByTran(MarketActivity activity);
}

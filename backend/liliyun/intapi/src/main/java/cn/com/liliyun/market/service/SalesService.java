package cn.com.liliyun.market.service;

import java.util.List;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.market.model.SalesActivity;
import cn.com.liliyun.market.model.SalesActivityClassinfo;

public interface SalesService {

	ResultBean addSalesActivity(SalesActivity activity,String businessid);
	
	List<SalesActivity> listActivity(SalesActivity activity);
	
	List<SalesActivity> listMatchActivity(SalesActivityClassinfo activity);
	
	ResultBean updateSalesActivity(SalesActivity activity);
	
	SalesActivity getSalesActivity(SalesActivity activity);
	
	ResultBean auditSalesActivity(SalesActivity activity);
	
	ResultBean batchSalesMarketActivity(String[] applyid,int state);	
	
	SalesActivityClassinfo getMatchActivityClass(SalesActivityClassinfo activity);
	
}

package cn.com.liliyun.market.service;

import java.util.List;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.market.model.SalesActivity;
import cn.com.liliyun.market.model.SalesActivityClassinfo;

public interface SalesService {

	public ResultBean addSalesActivity(SalesActivity activity,String businessid);
	
	public List<SalesActivity> listActivity(SalesActivity activity);
	
	public List<SalesActivity> listMatchActivity(SalesActivityClassinfo activity);
	
	public ResultBean updateSalesActivity(SalesActivity activity);
	
	public SalesActivity getSalesActivity(SalesActivity activity);
	
	public ResultBean auditSalesActivity(SalesActivity activity);
	
	public ResultBean batchSalesMarketActivity(String[] applyid,int state);	
	
	public SalesActivityClassinfo getMatchActivityClass(SalesActivityClassinfo activity);
	
}

package cn.com.liliyun.trainorg.service;

import java.util.List;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.trainorg.model.Marketing;
import cn.com.liliyun.trainorg.model.Visit;

public interface MarketingService {
	
	public ResultBean addMarket(Marketing marketing);
	
	public ResultBean deleteMarket(Marketing marketing);
	
	public ResultBean updateMarket(Marketing marketing);
		
	public ResultBean getMarketList(Marketing marketing);
	
	public ResultBean getMarketingById(Marketing marketing);
	
	//新增回访
	public ResultBean addVisit(Visit visit);
	//回访列表
	public ResultBean getVisitList(Visit visit);
	
	public void improtExcel(Marketing marketing,List list);
}

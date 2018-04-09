package cn.com.liliyun.trainorg.service;

import java.util.List;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.trainorg.model.Marketing;
import cn.com.liliyun.trainorg.model.Visit;

public interface MarketingService {
	
	ResultBean addMarket(Marketing marketing);
	
	ResultBean deleteMarket(Marketing marketing);
	
	ResultBean updateMarket(Marketing marketing);
		
	ResultBean getMarketList(Marketing marketing);
	
	ResultBean getMarketingById(Marketing marketing);
	
	//新增回访
	ResultBean addVisit(Visit visit);
	//回访列表
	ResultBean getVisitList(Visit visit);
	
	void improtExcel(Marketing marketing,List list);
}

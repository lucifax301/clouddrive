package cn.com.liliyun.market.mapper;

import java.util.List;

import cn.com.liliyun.coach.model.CoachModApply;
import cn.com.liliyun.coach.model.CoachModApplyParam;
import cn.com.liliyun.market.model.MarketActivity;

public interface MarketMapper {

	public void addMarket(MarketActivity market);
	
	public void updateMarket(MarketActivity market);
	
	public void updateMarketStatus(MarketActivity apply);
	
	public List<MarketActivity> listMarket(MarketActivity param);
	
	public MarketActivity getMarket(MarketActivity market);
	
	public MarketActivity getMarketByTran(MarketActivity market);
	
	
}

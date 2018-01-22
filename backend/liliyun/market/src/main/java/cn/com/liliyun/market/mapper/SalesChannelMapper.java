package cn.com.liliyun.market.mapper;

import java.util.List;
import java.util.Map;

import cn.com.liliyun.market.model.MarketActivity;
import cn.com.liliyun.market.model.SalesChannel;

public interface SalesChannelMapper {

	public void addChannel(SalesChannel channel);
	
	public void delChannel(SalesChannel channel);
	
	public void batchAddChannel(Map param);
	
	public void batchDelChannel(Map param);
	
	public void updateChannel(SalesChannel channel);
	
	public void updateChannelStatus(SalesChannel channel);
	
	public List<SalesChannel> listChannel(SalesChannel channel);
	
	public SalesChannel getChannel(SalesChannel channel);
	
	
}

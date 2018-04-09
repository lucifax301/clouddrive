package cn.com.liliyun.market.service;

import java.util.List;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.market.model.SalesChannel;


public interface SalesChannelService {

	ResultBean addChannel(SalesChannel channel);
	
	ResultBean updateChannel(SalesChannel channel);
	
	ResultBean updateChannelStatus(SalesChannel channel);
	
	ResultBean delChannel(SalesChannel channel);
	
	List<SalesChannel> selectChannels(SalesChannel channel);
	
	SalesChannel getChannel(SalesChannel channel);
	
	List<SalesChannel> selectAllChannels(SalesChannel channel);
}

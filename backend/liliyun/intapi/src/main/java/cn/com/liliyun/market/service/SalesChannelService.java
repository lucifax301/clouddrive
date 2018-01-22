package cn.com.liliyun.market.service;

import java.util.List;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.market.model.SalesChannel;
import cn.com.liliyun.user.model.User;


public interface SalesChannelService {

	public ResultBean addChannel(SalesChannel channel,User user);
	
	public ResultBean updateChannel(SalesChannel channel,User user);
	
	public ResultBean updateChannelStatus(SalesChannel channel);
	
	public ResultBean delChannel(SalesChannel channel);
	
	public List<SalesChannel> selectChannels(SalesChannel channel);
	
	public SalesChannel getChannel(SalesChannel channel);
	
	public List<SalesChannel> selectAllChannels(SalesChannel channel);
}

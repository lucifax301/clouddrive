package cn.com.liliyun.market.mapper;

import java.util.List;

import cn.com.liliyun.market.model.EnrolChannelStatItem;
import cn.com.liliyun.market.model.EnrolClassStatItem;
import cn.com.liliyun.market.model.EnrolDetailParam;



public interface EnrolStatMapper {

	public List<EnrolClassStatItem> statByClass(EnrolDetailParam param);
	
	public List<EnrolClassStatItem> getClassReturnCount(EnrolDetailParam param);
	
	
	public List<EnrolChannelStatItem> statByChannel(EnrolDetailParam param);
	
	public List<EnrolChannelStatItem> getChannelReturnCount(EnrolDetailParam param);
	
}

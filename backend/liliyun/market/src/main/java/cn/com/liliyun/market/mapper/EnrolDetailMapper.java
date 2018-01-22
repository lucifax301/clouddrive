package cn.com.liliyun.market.mapper;

import java.util.List;

import cn.com.liliyun.market.model.EnrolDetailItem;
import cn.com.liliyun.market.model.EnrolDetailParam;



public interface EnrolDetailMapper {

	public List<EnrolDetailItem> statByArea(EnrolDetailParam param);
	
	public List<EnrolDetailItem> getAreaReturnCount(EnrolDetailParam param);
	
	public List<EnrolDetailItem> statByStore(EnrolDetailParam param);
	
	public List<EnrolDetailItem> getStoreReturnCount(EnrolDetailParam param);
}

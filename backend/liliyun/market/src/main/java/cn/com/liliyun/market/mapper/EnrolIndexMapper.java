package cn.com.liliyun.market.mapper;

import java.util.List;
import java.util.Map;

import cn.com.liliyun.market.model.AreaEnrolIndex;
import cn.com.liliyun.market.model.StoreEnrolIndex;

public interface EnrolIndexMapper {

	public void addAreaEnrolIndex(AreaEnrolIndex index);
	
	public void delAreaEnrolIndex(AreaEnrolIndex index);
	
	public List<AreaEnrolIndex> listAreaEnrolIndex(AreaEnrolIndex param);
	
	public AreaEnrolIndex getAreaEnrolIndex(AreaEnrolIndex index);
	
	public AreaEnrolIndex getAreaEnrolIndexByDate(AreaEnrolIndex index);
	
	
	public void updateAreaEnrolIndex(AreaEnrolIndex index);
	
	
	
	public void batchAddStoreEnrolIndex(Map data);
	
	public void updateStoreEnrolIndex(StoreEnrolIndex index);
	
	public List<StoreEnrolIndex> listStoreEnrolIndex(StoreEnrolIndex param);
	
	public void delStoreEnrolIndex(StoreEnrolIndex index);
	
	
}

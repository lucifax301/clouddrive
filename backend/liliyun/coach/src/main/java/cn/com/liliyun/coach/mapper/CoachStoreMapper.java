package cn.com.liliyun.coach.mapper;

import java.util.List;
import java.util.Map;

import cn.com.liliyun.coach.model.CoachStore;
import cn.com.liliyun.trainorg.model.Store;

public interface CoachStoreMapper {

	public void add(CoachStore info);
	
	public void batchAdd(Map map);
	
	public void batchDel(Map map);
	
	public List<CoachStore> list(CoachStore info);

	public List<Store> batchSelectCount(Map map);
}

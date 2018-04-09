package cn.com.liliyun.trainorg.service;

import java.util.List;
import java.util.Map;

import cn.com.liliyun.common.dto.MapDTO;
import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.trainorg.model.Store;

/**
 * 培训机构-门店
 * @author lilixc
 *
 */
public interface StoreService {
	
	ResultBean insert(Store store);

	ResultBean selectByPrimaryKey(Store store);
	
	ResultBean updateByPrimaryKeySelective(Store store);

	ResultBean updateByPrimaryKey(Store store);

	ResultBean deleteById(Store store);
	
	List<Store> selectList(Store store);
	
	List<Store> selectList(Store store, Boolean isStorePage);
	
	List<Store> selectAllList(Store store);
	
	Store selectOne(Store store);
	
	int getCount(Store store);
	
	Map<Integer,MapDTO> getMap(Store store);
	
}

package cn.com.liliyun.trainorg.service;

import java.util.List;
import java.util.Map;

import cn.com.liliyun.common.dto.MapDTO;
import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.trainorg.model.Store;
import cn.com.liliyun.user.model.User;

/**
 * 培训机构-门店
 * @author lilixc
 *
 */
public interface StoreService {
	
	ResultBean insert(Store store, User user);

	ResultBean selectByPrimaryKey(Store store, User user);
	
	ResultBean updateByPrimaryKeySelective(Store store, User user);

	ResultBean updateByPrimaryKey(Store store, User user);

	ResultBean deleteById(Store store, User user);
	
	List<Store> selectList(Store store, User user);
	
	List<Store> selectList(Store store, User user, Boolean isStorePage);
	
	List<Store> selectAllList(Store store, User user);
	
	Store selectOne(Store store, User user);
	
	int getCount(Store store, User user);
	
	public Map<Integer,MapDTO> getMap(Store store);
	
}

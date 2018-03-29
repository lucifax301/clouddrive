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
	
	public ResultBean insert(Store store);

	public ResultBean selectByPrimaryKey(Store store);
	
	public ResultBean updateByPrimaryKeySelective(Store store);

	public ResultBean updateByPrimaryKey(Store store);

	public ResultBean deleteById(Store store);
	
	public List<Store> selectList(Store store);
	
	public List<Store> selectList(Store store, Boolean isStorePage);
	
	public List<Store> selectAllList(Store store);
	
	public Store selectOne(Store store);
	
	public int getCount(Store store);
	
	public Map<Integer,MapDTO> getMap(Store store);
	
}

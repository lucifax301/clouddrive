package cn.com.liliyun.student.mapper;

import java.util.List;
import java.util.Map;

import cn.com.liliyun.student.model.TransfertableItem;

public interface TransfertableItemMapper {
    int deleteByPrimaryKey(TransfertableItem record);

	TransfertableItem selectByPrimaryKey(TransfertableItem record);

	int updateByPrimaryKeySelective(TransfertableItem record);

	int updateByPrimaryKey(TransfertableItem record);
	
	int updateStoreReturn(TransfertableItem record);
	
	int updateAreaReturn(TransfertableItem record);

	int insert(TransfertableItem record);

    int insertSelective(TransfertableItem record);
    
    List<TransfertableItem> selectList(TransfertableItem record);
    
    int insertBatch(Map <String,Object> params);
    
    int updateTableIdBatch(Map <String,Object> params);
    
    int updateStatus(TransfertableItem record);
}
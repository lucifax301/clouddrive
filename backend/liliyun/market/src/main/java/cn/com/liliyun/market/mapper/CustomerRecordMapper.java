package cn.com.liliyun.market.mapper;

import java.util.List;
import java.util.Map;

import cn.com.liliyun.market.model.CustomerRecord;

public interface CustomerRecordMapper {
    int deleteByPrimaryKey(Map<String, Object> map);

    int insert(CustomerRecord record);

    int insertSelective(CustomerRecord record);

    CustomerRecord selectByPrimaryKey(Map<String, Object> map);

    int updateByPrimaryKeySelective(CustomerRecord record);

    int updateByPrimaryKey(CustomerRecord record);
    
    List<CustomerRecord> selectlist(CustomerRecord customerRecord);
    
    int handleRecordBatch(Map<String, Object> map);
}
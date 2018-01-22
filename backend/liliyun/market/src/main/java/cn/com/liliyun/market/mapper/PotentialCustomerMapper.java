package cn.com.liliyun.market.mapper;

import java.util.List;
import java.util.Map;

import cn.com.liliyun.market.model.PotentialCustomer;

public interface PotentialCustomerMapper {
    int deleteByPrimaryKey(Map<String, Object> map);

    int insert(PotentialCustomer record);

    int insertSelective(PotentialCustomer record);

    PotentialCustomer selectByPrimaryKey(Map<String, Object> map);

    int updateByPrimaryKeySelective(PotentialCustomer record);

    int updateByPrimaryKey(PotentialCustomer record);
    
    List<PotentialCustomer> selectList(PotentialCustomer record);
}
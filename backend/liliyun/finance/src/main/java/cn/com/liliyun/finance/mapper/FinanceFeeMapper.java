package cn.com.liliyun.finance.mapper;

import java.util.List;

import cn.com.liliyun.finance.model.FinanceFee;

public interface FinanceFeeMapper {
    int deleteByPrimaryKey(FinanceFee record);

    int insert(FinanceFee record);

    int insertSelective(FinanceFee record);

    FinanceFee selectByPrimaryKey(FinanceFee record);

    int updateByPrimaryKeySelective(FinanceFee record);

    int updateByPrimaryKey(FinanceFee record);
    
    List<FinanceFee> selectList(FinanceFee record);
}
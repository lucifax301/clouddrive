package cn.com.liliyun.finance.mapper;

import java.util.List;
import java.util.Map;

import cn.com.liliyun.finance.model.FinanceFeeItem;
import cn.com.liliyun.finance.model.FinancePay;

public interface FinanceFeeItemMapper {

        int deleteByPrimaryKey(FinanceFeeItem record);

        int insert(FinanceFeeItem record);

        int insertSelective(FinanceFeeItem record);

        FinanceFeeItem selectByPrimaryKey(FinanceFeeItem record);

        int updateByPrimaryKeySelective(FinanceFeeItem record);

        int updateByPrimaryKey(FinanceFeeItem record);

        int insertBatch(Map <String,Object> params);

        List <FinanceFeeItem> selectList(FinanceFeeItem feeItem);

        List <FinanceFeeItem> selectAllList(FinanceFeeItem feeItem);

        List <FinancePay> selectPay(FinancePay financePay);
        }
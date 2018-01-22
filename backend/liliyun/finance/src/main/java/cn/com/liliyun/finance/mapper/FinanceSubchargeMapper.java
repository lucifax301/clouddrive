package cn.com.liliyun.finance.mapper;

import java.util.List;

import cn.com.liliyun.finance.model.FinanceSubcharge;

public interface FinanceSubchargeMapper {
    int deleteByPrimaryKey(FinanceSubcharge financeSubcharge);

    int insert(FinanceSubcharge financeSubcharge);

    int insertSelective(FinanceSubcharge financeSubcharge);

    FinanceSubcharge selectByPrimaryKey(FinanceSubcharge financeSubcharge);

    int updateByPrimaryKeySelective(FinanceSubcharge financeSubcharge);

    int updateByPrimaryKey(FinanceSubcharge financeSubcharge);

    List<FinanceSubcharge> selectList(FinanceSubcharge financeSubcharge);

    int deleteByIds(FinanceSubcharge financeSubcharge);
}
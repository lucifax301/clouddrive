package cn.com.liliyun.finance.mapper;

import java.util.List;

import cn.com.liliyun.finance.model.FinancePos;

public interface FinancePosMapper {
    int deleteByPrimaryKey(FinancePos financePos);

    int insert(FinancePos financePos);

    int insertSelective(FinancePos financePos);

    FinancePos selectByPrimaryKey(FinancePos financePos);

    int updateByPrimaryKeySelective(FinancePos financePos);

    int updateByPrimaryKey(FinancePos financePos);
    
    List<FinancePos> selectList(FinancePos financePos);
    
    int deleteByIds(FinancePos financePos);
    
    FinancePos selectByPosnum(FinancePos financePos);
    
    List<FinancePos> selectPoscompany(FinancePos financePos);
    
    List<FinancePos> selectPosbankname(FinancePos financePos);
}
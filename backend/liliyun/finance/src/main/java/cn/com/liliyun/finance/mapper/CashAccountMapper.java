package cn.com.liliyun.finance.mapper;

import java.util.List;

import cn.com.liliyun.finance.model.CashAccount;

public interface CashAccountMapper {
    int deleteByPrimaryKey(CashAccount cashAccount);

    int insert(CashAccount cashAccount);

    int insertSelective(CashAccount cashAccount);

    CashAccount selectByPrimaryKey(CashAccount cashAccount);

    int updateByPrimaryKeySelective(CashAccount cashAccount);

    int updateByPrimaryKey(CashAccount cashAccount);
    
    List<CashAccount> selectList(CashAccount cashAccount);
    
    int deleteByIds(CashAccount cashAccount);
    
    CashAccount selectByAccount(CashAccount cashAccount);
}
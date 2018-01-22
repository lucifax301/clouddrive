package cn.com.liliyun.finance.mapper;

import java.util.List;

import cn.com.liliyun.finance.model.FinanceDeposit;

public interface FinanceDepositMapper {
    int deleteByPrimaryKey(FinanceDeposit financeDeposit);

    int insert(FinanceDeposit financeDeposit);

    int insertSelective(FinanceDeposit financeDeposit);

    FinanceDeposit selectByPrimaryKey(FinanceDeposit financeDeposit);

    /**
     * mtime != null时，判断ctime和mtime是不是同一天，非同一天不允许修改；
     * mtime == null时，不受影响
     * @param financeDeposit
     * @return
     */
    int updateByPrimaryKeySelective(FinanceDeposit financeDeposit);

    int updateByPrimaryKey(FinanceDeposit financeDeposit);
    
    List<FinanceDeposit> selectList(FinanceDeposit financeDeposit);
    
    int deleteByIds(FinanceDeposit financeDeposit);
    
    int updateConfirmByIds(FinanceDeposit financeDeposit);
}
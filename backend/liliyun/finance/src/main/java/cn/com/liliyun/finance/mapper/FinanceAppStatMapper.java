package cn.com.liliyun.finance.mapper;

import java.util.List;

import cn.com.liliyun.finance.model.FinanceAppStat;

public interface FinanceAppStatMapper
{
  public abstract List<FinanceAppStat> selectIncomeStat(FinanceAppStat paramFinanceAppStat);
  
  public abstract List<FinanceAppStat> selectOutcomeStat(FinanceAppStat paramFinanceAppStat);
}
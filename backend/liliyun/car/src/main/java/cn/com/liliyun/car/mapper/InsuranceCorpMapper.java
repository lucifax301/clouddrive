package cn.com.liliyun.car.mapper;

import cn.com.liliyun.car.model.InsuranceCorp;
import java.util.List;

public abstract interface InsuranceCorpMapper
{
  public abstract int delete(InsuranceCorp paramInsuranceCorp);
  
  public abstract int insert(InsuranceCorp paramInsuranceCorp);
  
  public abstract int updateStatus(InsuranceCorp paramInsuranceCorp);
  
  public abstract int update(InsuranceCorp paramInsuranceCorp);
  
  public abstract List<InsuranceCorp> list(InsuranceCorp paramInsuranceCorp);
  
  public abstract InsuranceCorp getByName(InsuranceCorp paramInsuranceCorp);
}

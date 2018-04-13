package cn.com.liliyun.car.service;

import cn.com.liliyun.car.model.InsuranceCorp;
import cn.com.liliyun.common.model.ResultBean;
import java.util.List;

public abstract interface InsuranceCorpService
{
  public abstract ResultBean add(InsuranceCorp paramInsuranceCorp);
  
  public abstract ResultBean update(InsuranceCorp paramInsuranceCorp);
  
  public abstract ResultBean delete(InsuranceCorp paramInsuranceCorp);
  
  public abstract ResultBean enable(InsuranceCorp paramInsuranceCorp);
  
  public abstract List<InsuranceCorp> list(InsuranceCorp paramInsuranceCorp);
}

package cn.com.liliyun.car.service.impl;

import cn.com.liliyun.car.mapper.InsuranceCorpMapper;
import cn.com.liliyun.car.model.InsuranceCorp;
import cn.com.liliyun.car.service.InsuranceCorpService;
import cn.com.liliyun.common.model.ResultBean;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InsuranceCorpServiceImpl
  implements InsuranceCorpService
{
  @Autowired
  private InsuranceCorpMapper insuranceCorpMapper;
  
  public ResultBean add(InsuranceCorp corp)
  {
    InsuranceCorp exist = this.insuranceCorpMapper.getByName(corp);
    if (exist != null)
    {
      ResultBean rb = new ResultBean("保险公司名称已经存在");
      return rb;
    }
    this.insuranceCorpMapper.insert(corp);
    return new ResultBean();
  }
  
  public ResultBean update(InsuranceCorp corp)
  {
    InsuranceCorp exist = this.insuranceCorpMapper.getByName(corp);
    if ((exist != null) && (exist.getId().intValue() != corp.getId().intValue()))
    {
      ResultBean rb = new ResultBean("保险公司名称已经存在");
      return rb;
    }
    this.insuranceCorpMapper.update(corp);
    return new ResultBean();
  }
  
  public ResultBean delete(InsuranceCorp corp)
  {
    this.insuranceCorpMapper.delete(corp);
    return new ResultBean();
  }
  
  public ResultBean enable(InsuranceCorp corp)
  {
    this.insuranceCorpMapper.updateStatus(corp);
    return new ResultBean();
  }
  
  public List<InsuranceCorp> list(InsuranceCorp corp)
  {
    return this.insuranceCorpMapper.list(corp);
  }
}

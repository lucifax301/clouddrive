package cn.com.liliyun.car.service.impl;

import cn.com.liliyun.car.mapper.RegOfficeMapper;
import cn.com.liliyun.car.model.RegOffice;
import cn.com.liliyun.car.service.RegOfficeService;
import cn.com.liliyun.common.model.ResultBean;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegOfficeServiceImpl
  implements RegOfficeService
{
  @Autowired
  private RegOfficeMapper regOfficeMapper;
  
  public ResultBean add(RegOffice office)
  {
    RegOffice exist = this.regOfficeMapper.getByName(office);
    if (exist != null)
    {
      ResultBean rb = new ResultBean("登记机关名称已经存在");
      return rb;
    }
    this.regOfficeMapper.insert(office);
    return new ResultBean();
  }
  
  public ResultBean update(RegOffice office)
  {
    RegOffice exist = this.regOfficeMapper.getByName(office);
    if ((exist != null) && (exist.getId().intValue() != office.getId().intValue()))
    {
      ResultBean rb = new ResultBean("登记机关名称已经存在");
      return rb;
    }
    this.regOfficeMapper.update(office);
    return new ResultBean();
  }
  
  public ResultBean delete(RegOffice office)
  {
    this.regOfficeMapper.delete(office);
    return new ResultBean();
  }
  
  public ResultBean enable(RegOffice office)
  {
    this.regOfficeMapper.updateStatus(office);
    return new ResultBean();
  }
  
  public List<RegOffice> list(RegOffice office)
  {
    return this.regOfficeMapper.list(office);
  }
}

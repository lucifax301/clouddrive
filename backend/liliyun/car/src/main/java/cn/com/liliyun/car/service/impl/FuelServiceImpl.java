package cn.com.liliyun.car.service.impl;

import cn.com.liliyun.car.mapper.FuelMapper;
import cn.com.liliyun.car.model.Fuel;
import cn.com.liliyun.car.service.FuelService;
import cn.com.liliyun.common.model.ResultBean;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FuelServiceImpl
  implements FuelService
{
  @Autowired
  private FuelMapper FuelMapper;
  
  public ResultBean add(Fuel office)
  {
    Fuel exist = this.FuelMapper.getByName(office);
    if (exist != null)
    {
      ResultBean rb = new ResultBean("油品名称已经存在");
      return rb;
    }
    this.FuelMapper.insert(office);
    return new ResultBean();
  }
  
  public ResultBean update(Fuel office)
  {
    Fuel exist = this.FuelMapper.getByName(office);
    if ((exist != null) && (exist.getId().intValue() != office.getId().intValue()))
    {
      ResultBean rb = new ResultBean("油品名称已经存在");
      return rb;
    }
    this.FuelMapper.update(office);
    return new ResultBean();
  }
  
  public ResultBean delete(Fuel office)
  {
    this.FuelMapper.delete(office);
    return new ResultBean();
  }
  
  public ResultBean enable(Fuel office)
  {
    this.FuelMapper.updateStatus(office);
    return new ResultBean();
  }
  
  public List<Fuel> list(Fuel office)
  {
    return this.FuelMapper.list(office);
  }
}

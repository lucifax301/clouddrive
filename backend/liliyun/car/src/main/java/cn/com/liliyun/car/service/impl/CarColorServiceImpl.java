package cn.com.liliyun.car.service.impl;

import cn.com.liliyun.car.mapper.CarColorMapper;
import cn.com.liliyun.car.model.CarColor;
import cn.com.liliyun.car.service.CarColorService;
import cn.com.liliyun.common.model.ResultBean;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarColorServiceImpl
  implements CarColorService
{
  @Autowired
  private CarColorMapper CarColorMapper;
  
  public ResultBean add(CarColor office)
  {
    CarColor exist = this.CarColorMapper.getByName(office);
    if (exist != null)
    {
      ResultBean rb = new ResultBean("车身颜色名称已经存在");
      return rb;
    }
    this.CarColorMapper.insert(office);
    return new ResultBean();
  }
  
  public ResultBean update(CarColor office)
  {
    CarColor exist = this.CarColorMapper.getByName(office);
    if ((exist != null) && (exist.getId().intValue() != office.getId().intValue()))
    {
      ResultBean rb = new ResultBean("车身颜色名称已经存在");
      return rb;
    }
    this.CarColorMapper.update(office);
    return new ResultBean();
  }
  
  public ResultBean delete(CarColor office)
  {
    this.CarColorMapper.delete(office);
    return new ResultBean();
  }
  
  public ResultBean enable(CarColor office)
  {
    this.CarColorMapper.updateStatus(office);
    return new ResultBean();
  }
  
  public List<CarColor> list(CarColor office)
  {
    return this.CarColorMapper.list(office);
  }
}

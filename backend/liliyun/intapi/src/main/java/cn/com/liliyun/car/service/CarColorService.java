package cn.com.liliyun.car.service;

import cn.com.liliyun.car.model.CarColor;
import cn.com.liliyun.common.model.ResultBean;
import java.util.List;

public abstract interface CarColorService
{
  public abstract ResultBean add(CarColor paramCarColor);
  
  public abstract ResultBean update(CarColor paramCarColor);
  
  public abstract ResultBean delete(CarColor paramCarColor);
  
  public abstract ResultBean enable(CarColor paramCarColor);
  
  public abstract List<CarColor> list(CarColor paramCarColor);
}

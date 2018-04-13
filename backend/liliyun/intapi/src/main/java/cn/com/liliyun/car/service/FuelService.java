package cn.com.liliyun.car.service;

import cn.com.liliyun.car.model.Fuel;
import cn.com.liliyun.common.model.ResultBean;
import java.util.List;

public abstract interface FuelService
{
  public abstract ResultBean add(Fuel paramFuel);
  
  public abstract ResultBean update(Fuel paramFuel);
  
  public abstract ResultBean delete(Fuel paramFuel);
  
  public abstract ResultBean enable(Fuel paramFuel);
  
  public abstract List<Fuel> list(Fuel paramFuel);
}

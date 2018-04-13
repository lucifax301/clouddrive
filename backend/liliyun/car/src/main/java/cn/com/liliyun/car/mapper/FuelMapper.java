package cn.com.liliyun.car.mapper;

import cn.com.liliyun.car.model.Fuel;
import java.util.List;

public abstract interface FuelMapper
{
  public abstract int delete(Fuel paramFuel);
  
  public abstract int insert(Fuel paramFuel);
  
  public abstract int updateStatus(Fuel paramFuel);
  
  public abstract int update(Fuel paramFuel);
  
  public abstract List<Fuel> list(Fuel paramFuel);
  
  public abstract Fuel getByName(Fuel paramFuel);
}

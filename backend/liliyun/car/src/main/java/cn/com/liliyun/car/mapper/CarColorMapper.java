package cn.com.liliyun.car.mapper;

import cn.com.liliyun.car.model.CarColor;
import java.util.List;

public abstract interface CarColorMapper
{
  public abstract int delete(CarColor paramCarColor);
  
  public abstract int insert(CarColor paramCarColor);
  
  public abstract int updateStatus(CarColor paramCarColor);
  
  public abstract int update(CarColor paramCarColor);
  
  public abstract List<CarColor> list(CarColor paramCarColor);
  
  public abstract CarColor getByName(CarColor paramCarColor);
}

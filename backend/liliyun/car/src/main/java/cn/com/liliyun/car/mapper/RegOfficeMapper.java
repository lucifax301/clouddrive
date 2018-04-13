package cn.com.liliyun.car.mapper;

import cn.com.liliyun.car.model.RegOffice;
import java.util.List;

public abstract interface RegOfficeMapper
{
  public abstract int delete(RegOffice paramRegOffice);
  
  public abstract int insert(RegOffice paramRegOffice);
  
  public abstract int updateStatus(RegOffice paramRegOffice);
  
  public abstract int update(RegOffice paramRegOffice);
  
  public abstract List<RegOffice> list(RegOffice paramRegOffice);
  
  public abstract RegOffice getByName(RegOffice paramRegOffice);
}

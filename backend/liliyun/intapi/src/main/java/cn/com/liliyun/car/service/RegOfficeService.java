package cn.com.liliyun.car.service;

import cn.com.liliyun.car.model.RegOffice;
import cn.com.liliyun.common.model.ResultBean;
import java.util.List;

public abstract interface RegOfficeService
{
  public abstract ResultBean add(RegOffice paramRegOffice);
  
  public abstract ResultBean update(RegOffice paramRegOffice);
  
  public abstract ResultBean delete(RegOffice paramRegOffice);
  
  public abstract ResultBean enable(RegOffice paramRegOffice);
  
  public abstract List<RegOffice> list(RegOffice paramRegOffice);
}

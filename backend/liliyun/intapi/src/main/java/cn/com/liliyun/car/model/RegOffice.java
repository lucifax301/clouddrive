package cn.com.liliyun.car.model;

import cn.com.liliyun.common.model.BaseModel;
import java.io.Serializable;
import java.util.Date;

public class RegOffice
  extends BaseModel
  implements Serializable
{
  private Integer id;
  private String name;
  private Integer status;
  private Integer isdel;
  private Integer cuid;
  private Date ctime;
  
  public Integer getId()
  {
    return this.id;
  }
  
  public void setId(Integer id)
  {
    this.id = id;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public void setName(String name)
  {
    this.name = name;
  }
  
  public Integer getStatus()
  {
    return this.status;
  }
  
  public void setStatus(Integer status)
  {
    this.status = status;
  }
  
  public Integer getIsdel()
  {
    return this.isdel;
  }
  
  public void setIsdel(Integer isdel)
  {
    this.isdel = isdel;
  }
  
  public Integer getCuid()
  {
    return this.cuid;
  }
  
  public void setCuid(Integer cuid)
  {
    this.cuid = cuid;
  }
  
  public Date getCtime()
  {
    return this.ctime;
  }
  
  public void setCtime(Date ctime)
  {
    this.ctime = ctime;
  }
}

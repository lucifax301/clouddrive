package cn.com.liliyun.httpaccess.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.liliyun.car.model.Fuel;
import cn.com.liliyun.car.service.FuelService;
import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.httpaccess.util.AccessWebUtil;
import cn.com.liliyun.user.model.User;

import com.github.pagehelper.PageInfo;

@Controller
@ResponseBody
@RequestMapping({"/fuel"})
public class FuelController
  extends BaseController
{
  @Autowired
  private FuelService FuelService;
  
  @RequestMapping({"/add"})
  public ResultBean add(Fuel office, HttpServletRequest request)
  {
    User user = AccessWebUtil.getSessionUser(request);
    office.setCuid(user.getId());
    ResultBean rb = this.FuelService.add(office);
    return rb;
  }
  
  @RequestMapping({"/update"})
  public ResultBean update(Fuel office, HttpServletRequest request)
  {
    User user = AccessWebUtil.getSessionUser(request);
    
    ResultBean rb = this.FuelService.update(office);
    return rb;
  }
  
  @RequestMapping({"/enable"})
  public ResultBean updateStatus(Fuel office, HttpServletRequest request)
  {
    User user = AccessWebUtil.getSessionUser(request);
    
    ResultBean rb = this.FuelService.enable(office);
    return rb;
  }
  
  @RequestMapping({"/del"})
  public ResultBean del(Fuel office, HttpServletRequest request)
  {
    User user = AccessWebUtil.getSessionUser(request);
    
    ResultBean rb = this.FuelService.delete(office);
    return rb;
  }
  
  @RequestMapping({"/list"})
  public ResultBean getList(Fuel office, HttpServletRequest request)
  {
    User user = AccessWebUtil.getSessionUser(request);
    
    ResultBean rb = new ResultBean();
    
    List<Fuel> list = this.FuelService.list(office);
    rb.setResult(new PageInfo(list));
    return rb;
  }
}

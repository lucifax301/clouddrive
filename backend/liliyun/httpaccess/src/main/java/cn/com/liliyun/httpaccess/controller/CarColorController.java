package cn.com.liliyun.httpaccess.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.liliyun.car.model.CarColor;
import cn.com.liliyun.car.service.CarColorService;
import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.httpaccess.util.AccessWebUtil;
import cn.com.liliyun.user.model.User;

import com.github.pagehelper.PageInfo;

@Controller
@ResponseBody
@RequestMapping({"/carcolor"})
public class CarColorController
  extends BaseController
{
  @Autowired
  private CarColorService carColorService;
  
  @RequestMapping({"/add"})
  public ResultBean add(CarColor office, HttpServletRequest request)
  {
    User user = AccessWebUtil.getSessionUser(request);
    office.setCuid(user.getId());
    ResultBean rb = this.carColorService.add(office);
    return rb;
  }
  
  @RequestMapping({"/update"})
  public ResultBean update(CarColor office, HttpServletRequest request)
  {
    User user = AccessWebUtil.getSessionUser(request);
    
    ResultBean rb = this.carColorService.update(office);
    return rb;
  }
  
  @RequestMapping({"/enable"})
  public ResultBean updateStatus(CarColor office, HttpServletRequest request)
  {
    User user = AccessWebUtil.getSessionUser(request);
    
    ResultBean rb = this.carColorService.enable(office);
    return rb;
  }
  
  @RequestMapping({"/del"})
  public ResultBean del(CarColor office, HttpServletRequest request)
  {
    User user = AccessWebUtil.getSessionUser(request);
    
    ResultBean rb = this.carColorService.delete(office);
    return rb;
  }
  
  @RequestMapping({"/list"})
  public ResultBean getList(CarColor office, HttpServletRequest request)
  {
    User user = AccessWebUtil.getSessionUser(request);
    
    ResultBean rb = new ResultBean();
    
    List<CarColor> list = this.carColorService.list(office);
    rb.setResult(new PageInfo(list));
    return rb;
  }
}

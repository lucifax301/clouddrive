package cn.com.liliyun.httpaccess.controller;

import cn.com.liliyun.car.model.RegOffice;
import cn.com.liliyun.car.service.RegOfficeService;
import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.httpaccess.util.AccessWebUtil;
import cn.com.liliyun.user.model.User;
import com.github.pagehelper.PageInfo;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping({"/regoffice"})
public class RegOfficeController
  extends BaseController
{
  @Autowired
  private RegOfficeService regOfficeService;
  
  @RequestMapping({"/add"})
  public ResultBean add(RegOffice office, HttpServletRequest request)
  {
    User user = AccessWebUtil.getSessionUser(request);
    office.setCuid(user.getId());
    ResultBean rb = this.regOfficeService.add(office);
    return rb;
  }
  
  @RequestMapping({"/update"})
  public ResultBean update(RegOffice office, HttpServletRequest request)
  {
    User user = AccessWebUtil.getSessionUser(request);
    
    ResultBean rb = this.regOfficeService.update(office);
    return rb;
  }
  
  @RequestMapping({"/enable"})
  public ResultBean updateStatus(RegOffice office, HttpServletRequest request)
  {
    User user = AccessWebUtil.getSessionUser(request);
    
    ResultBean rb = this.regOfficeService.enable(office);
    return rb;
  }
  
  @RequestMapping({"/del"})
  public ResultBean del(RegOffice office, HttpServletRequest request)
  {
    User user = AccessWebUtil.getSessionUser(request);
    
    ResultBean rb = this.regOfficeService.delete(office);
    return rb;
  }
  
  @RequestMapping({"/list"})
  public ResultBean getList(RegOffice office, HttpServletRequest request)
  {
    User user = AccessWebUtil.getSessionUser(request);
    
    ResultBean rb = new ResultBean();
    
    List<RegOffice> list = this.regOfficeService.list(office);
    rb.setResult(new PageInfo(list));
    return rb;
  }
}

package cn.com.liliyun.httpaccess.controller;

import cn.com.liliyun.car.model.InsuranceCorp;
import cn.com.liliyun.car.service.InsuranceCorpService;
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
@RequestMapping({"/insurancecorp"})
public class InsuraceCorpController
  extends BaseController
{
  @Autowired
  private InsuranceCorpService insuranceCorpService;
  
  @RequestMapping({"/add"})
  public ResultBean add(InsuranceCorp corp, HttpServletRequest request)
  {
    User user = AccessWebUtil.getSessionUser(request);
    corp.setCuid(user.getId());
    ResultBean rb = this.insuranceCorpService.add(corp);
    return rb;
  }
  
  @RequestMapping({"/update"})
  public ResultBean update(InsuranceCorp corp, HttpServletRequest request)
  {
    User user = AccessWebUtil.getSessionUser(request);
    
    ResultBean rb = this.insuranceCorpService.update(corp);
    return rb;
  }
  
  @RequestMapping({"/enable"})
  public ResultBean updateStatus(InsuranceCorp corp, HttpServletRequest request)
  {
    User user = AccessWebUtil.getSessionUser(request);
    
    ResultBean rb = this.insuranceCorpService.enable(corp);
    return rb;
  }
  
  @RequestMapping({"/del"})
  public ResultBean del(InsuranceCorp corp, HttpServletRequest request)
  {
    User user = AccessWebUtil.getSessionUser(request);
    
    ResultBean rb = this.insuranceCorpService.delete(corp);
    return rb;
  }
  
  @RequestMapping({"/list"})
  public ResultBean getList(InsuranceCorp corp, HttpServletRequest request)
  {
    User user = AccessWebUtil.getSessionUser(request);
    
    ResultBean rb = new ResultBean();
    
    List<InsuranceCorp> list = this.insuranceCorpService.list(corp);
    rb.setResult(new PageInfo(list));
    return rb;
  }
}

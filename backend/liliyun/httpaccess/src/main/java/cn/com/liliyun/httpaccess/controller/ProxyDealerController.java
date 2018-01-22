package cn.com.liliyun.httpaccess.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.httpaccess.util.AccessWebUtil;
import cn.com.liliyun.trainorg.model.ProxyDealer;
import cn.com.liliyun.trainorg.service.ProxyDealerService;
import cn.com.liliyun.user.model.User;

@Controller
@ResponseBody
@RequestMapping(value="/proxy")
public class ProxyDealerController extends BaseController {

	@Autowired
	private ProxyDealerService proxyDealerService;
	
	@RequestMapping(value="/list")
	public ResultBean getList(ProxyDealer proxyDealer) {
		return proxyDealerService.getList(proxyDealer);
	}
	
	@RequestMapping(value="/deleteById")
	public ResultBean deleteById(ProxyDealer proxyDealer) {
		return proxyDealerService.deleteByPrimaryKey(proxyDealer);
	}

	@RequestMapping(value="/getById")
	public ResultBean getScopeById(ProxyDealer proxy) {
		return proxyDealerService.selectByPrimaryKey(proxy);
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public ResultBean addScope(ProxyDealer proxyDealer,HttpServletRequest request) {
		User user = AccessWebUtil.getSessionUser(request);
		String userName = "admin";
		if(user != null)	
			userName = user.getUsername();
		proxyDealer.setModifyUser(userName);
		return proxyDealerService.insertSelective(proxyDealer);
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public ResultBean updateScope(ProxyDealer proxyDealer,HttpServletRequest request) {
		User user = AccessWebUtil.getSessionUser(request);
		String userName = "admin";
		if(user != null)
			userName = user.getUsername();
		proxyDealer.setModifyUser(userName);
		return proxyDealerService.updateByPrimaryKeySelective(proxyDealer);
	}
	
	
	@RequestMapping(value = "/checkMutliName")
	public ResultBean checkMutliName(ProxyDealer proxy) {
		return proxyDealerService.checkMutliName(proxy);
	}
	
	@RequestMapping(value = "/disable")
	public ResultBean disable(ProxyDealer proxy) {
		return proxyDealerService.onlyUpdateStatus(proxy);
	}
	
	@RequestMapping(value = "/enable")
	public ResultBean enable(ProxyDealer proxy) {
		return proxyDealerService.onlyUpdateStatus(proxy);
	}
	
	@RequestMapping(value = "/getAllByScopeId")
	public ResultBean getAllByScopeId(ProxyDealer proxy) {
		return proxyDealerService.getAllByScopeId(proxy);
	}
}

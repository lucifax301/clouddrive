package cn.com.liliyun.httpaccess.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.liliyun.business.model.Business;
import cn.com.liliyun.business.service.BusinessService;
import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.httpaccess.util.AccessWebUtil;
import cn.com.liliyun.user.model.User;

@Controller
@ResponseBody
@RequestMapping(value = "/business")
public class BusinessController {

	private Logger logger = Logger.getLogger(BusinessController.class);
	
	@Autowired
	private BusinessService businessService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public ResultBean listbusiness(Business business) {
		ResultBean rb = new ResultBean();
		try {
			List<Business> bss = businessService.listBusiness(business);
			Map <String,Object> data = new HashMap<String, Object>();
			data.put("list", bss);
			rb.setResult(data);
		} catch (Exception ex) {
			logger.warn(ex.getStackTrace());
			rb.setMsg("error");
			rb.setCode(400);
		}
		return rb;
	}
	
	/**
	 * 添加/修改权限菜单
	 * @param privilege
	 * @return
	 */
	@RequestMapping(value = "/editBusiness")
	@ResponseBody
	public ResultBean editbusiness(Business business) {
		ResultBean rb = new ResultBean();
		try {
			if (business.getId() == null) {
				businessService.addBusiness(business);
			} else {
				businessService.updateBusiness(business);
			}
			
		} catch (Exception ex) {
			logger.warn(ex.getStackTrace());
			ex.printStackTrace();
			rb.setMsg("新增审批业务出错");
			rb.setCode(400);
		}
		return rb;
	}
	
	/**
	 * 删除权限菜单
	 * @param privilege
	 * @return
	 */
	@RequestMapping(value = "/delBusiness")
	@ResponseBody
	public ResultBean delbusiness(Business business, HttpServletRequest request) {
		ResultBean rb = new ResultBean();
		try {
			User user = AccessWebUtil.getSessionUser(request);
			businessService.delBusiness(business);
		} catch (Exception ex) {
			ex.printStackTrace();
			
			rb.setMsg("删除审批业务出错");
			rb.setCode(400);
		}
		return rb;
	}
}

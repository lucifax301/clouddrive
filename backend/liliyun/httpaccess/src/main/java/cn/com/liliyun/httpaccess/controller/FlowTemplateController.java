package cn.com.liliyun.httpaccess.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.liliyun.business.service.BusinessService;
import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.common.util.LogConstant;
import cn.com.liliyun.flow.model.Flow;
import cn.com.liliyun.flow.model.FlowTemplate;
import cn.com.liliyun.httpaccess.util.AccessWebUtil;
import cn.com.liliyun.log.model.LogCommon;
import cn.com.liliyun.market.model.SalesChannel;
import cn.com.liliyun.user.model.User;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

@Controller
@ResponseBody
@SuppressWarnings("unchecked")
@RequestMapping(value="/flowtemplate")
public class FlowTemplateController extends BaseController{

	private static Logger logger=Logger.getLogger(FlowTemplateController.class);
	
	@Autowired
	private BusinessService businessService;
	
	@RequestMapping(value = "/list")
	@ResponseBody
	public ResultBean list(FlowTemplate flowTemplate,HttpServletRequest request) {
		ResultBean rb = null;
		User user = AccessWebUtil.getSessionUser(request);
		try {
			List<FlowTemplate> list = businessService.listFlowTemplate(flowTemplate);
			PageInfo<FlowTemplate> pagedResult = new PageInfo(list);
			rb=new ResultBean();
			rb.setResult(pagedResult);
		} catch (Exception ex) {
			ex.printStackTrace();
			rb=new ResultBean();
			rb.setMsg("error");
			rb.setCode(400);
		}
		return rb;
	}
	
	@RequestMapping(value = "/get")
	@ResponseBody
	public ResultBean get(FlowTemplate flowTemplate) {
		ResultBean rb = null;
		
		try {
			FlowTemplate template = businessService.getFlowTemplate(flowTemplate);
			
			rb=new ResultBean();
			rb.setResult(template);
		} catch (Exception ex) {
			ex.printStackTrace();
			rb=new ResultBean();
			rb.setMsg("error");
			rb.setCode(400);
		}
		return rb;
	}
	
	@RequestMapping(value="/add")
	@ResponseBody
	public ResultBean addTemplate(FlowTemplate flowTemplate,HttpServletRequest request){
		
		try{
			LogCommon log = initLogParams(request, 0, LogConstant.ACTION_ADD);
			User user=AccessWebUtil.getSessionUser(request);
			
			return businessService.addFlowTemplate(flowTemplate);
			
		}catch(Exception ex){
			logger.error(ex);
			ex.printStackTrace();
			ResultBean rb = new ResultBean("新增审批模版出错");
			return rb;
		}
		
	}
	
	@RequestMapping(value="/delete")
	@ResponseBody
	public ResultBean deleteTemplate(FlowTemplate flowTemplate) {
		
		try{
			return businessService.delFlowTemplate(flowTemplate);
		}catch(Exception ex){
			ex.printStackTrace();
			ResultBean rb=new ResultBean("删除审批模版出错");
			return rb;
		}
		
	}
	
	@RequestMapping(value="/update")
	@ResponseBody
	public ResultBean updateTemplate(FlowTemplate flowTemplate,HttpServletRequest request) {
		
		try{
			LogCommon log = initLogParams(request, 0, LogConstant.ACTION_UPDATE);
			User user=AccessWebUtil.getSessionUser(request);
			
			return businessService.updateFlowTemplate(flowTemplate);
		}catch(Exception ex){
			ex.printStackTrace();
			ResultBean rb = new ResultBean("更新审批模版出错");
			return rb;
		}
		
	}
}

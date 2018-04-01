package cn.com.liliyun.httpaccess.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.liliyun.business.service.BusinessService;
import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.flow.model.FlowTemplate;

@Controller
@ResponseBody
@RequestMapping(value="/flowtemplate")
public class FlowTemplateController extends BaseController{

	//private static Logger logger=Logger.getLogger(FlowTemplateController.class);
	
	@Autowired
	private BusinessService businessService;
	
	@RequestMapping(value = "/list")
	@ResponseBody
	public ResultBean list(FlowTemplate flowTemplate,HttpServletRequest request) {
		List<FlowTemplate> list = businessService.listFlowTemplate(flowTemplate);
		return this.<FlowTemplate>buildListResult(list);
			
	}
	
	@RequestMapping(value = "/get")
	@ResponseBody
	public ResultBean get(FlowTemplate flowTemplate) {
		FlowTemplate template = businessService.getFlowTemplate(flowTemplate);
		return this.<FlowTemplate>buildResult(template);	
	}
	
	@RequestMapping(value="/add")
	@ResponseBody
	public ResultBean addTemplate(FlowTemplate flowTemplate,HttpServletRequest request){
		return businessService.addFlowTemplate(flowTemplate);
		
	}
	
	@RequestMapping(value="/delete")
	@ResponseBody
	public ResultBean deleteTemplate(FlowTemplate flowTemplate) {
		return businessService.delFlowTemplate(flowTemplate);
		
	}
	
	@RequestMapping(value="/update")
	@ResponseBody
	public ResultBean updateTemplate(FlowTemplate flowTemplate,HttpServletRequest request) {
		return businessService.updateFlowTemplate(flowTemplate);
		
		
	}
}

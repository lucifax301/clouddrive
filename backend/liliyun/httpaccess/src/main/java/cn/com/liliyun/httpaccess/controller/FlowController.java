package cn.com.liliyun.httpaccess.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.liliyun.business.service.BusinessService;
import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.flow.model.Flow;
import cn.com.liliyun.flow.model.FlowStep;

@Controller
@ResponseBody
@RequestMapping(value="/flow")
public class FlowController extends BaseController{

	//private Logger logger = Logger.getLogger(FlowController.class);
	
	@Autowired
	private BusinessService flowService;
	
	@RequestMapping(value = "/listmy")
	@ResponseBody
	public ResultBean listmy(Flow flow,HttpServletRequest request) {
		List<Flow> list = flowService.findMyFlow(flow);
		return this.<Flow>buildListResult(list);
	}
	
	@RequestMapping(value = "/list")
	@ResponseBody
	public ResultBean list(FlowStep flowstep,HttpServletRequest request) {
		List<Flow> list = flowService.findUserFlow(flowstep);
		return this.<Flow>buildListResult(list);
	}
	
	@RequestMapping(value = "/listflowstep")
	@ResponseBody
	public ResultBean listflowstep(FlowStep flowstep,HttpServletRequest request) {
		List<FlowStep> list = flowService.listFlowStep(flowstep);
		return this.<FlowStep>buildListResult(list);
	}
}

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
import cn.com.liliyun.flow.model.Flow;
import cn.com.liliyun.flow.model.FlowStep;
import cn.com.liliyun.httpaccess.util.AccessWebUtil;
import cn.com.liliyun.user.model.User;

import com.github.pagehelper.PageInfo;

@Controller
@ResponseBody
@SuppressWarnings("unchecked")
@RequestMapping(value="/flow")
public class FlowController {

	private Logger logger = Logger.getLogger(FlowController.class);
	
	@Autowired
	private BusinessService flowService;
	
	@RequestMapping(value = "/listmy")
	@ResponseBody
	public ResultBean listmy(Flow flow,HttpServletRequest request) {
		List<Flow> list = flowService.findMyFlow(flow);
		PageInfo<Flow> pagedResult = new PageInfo(list);
		ResultBean rb=new ResultBean();
		rb.setResult(pagedResult);
		return rb;
		
	}
	
	@RequestMapping(value = "/list")
	@ResponseBody
	public ResultBean list(FlowStep flowstep,HttpServletRequest request) {
		List<Flow> list = flowService.findUserFlow(flowstep);
		PageInfo<Flow> pagedResult = new PageInfo(list);
		ResultBean rb=new ResultBean();
		rb.setResult(pagedResult);
		return rb;
	}
	
	@RequestMapping(value = "/listflowstep")
	@ResponseBody
	public ResultBean listflowstep(FlowStep flowstep,HttpServletRequest request) {
		List<FlowStep> list = flowService.listFlowStep(flowstep);
		PageInfo<Flow> pagedResult = new PageInfo(list);
		ResultBean rb=new ResultBean();
		rb.setResult(pagedResult);
		return rb;
	}
}

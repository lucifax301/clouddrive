package cn.com.liliyun.httpaccess.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.httpaccess.util.AccessWebUtil;
import cn.com.liliyun.trainorg.model.Examarea;
import cn.com.liliyun.trainorg.service.ExamareaService;
import cn.com.liliyun.user.model.User;

@Controller
@ResponseBody
@RequestMapping(value="/examarea")
public class ExamareaController extends BaseController{

	@Autowired
	private ExamareaService examareaService;
	
	@RequestMapping(value="/edit")
	public ResultBean edit(Examarea trainarea,HttpServletRequest request) {
		ResultBean rb;
		User user=AccessWebUtil.getSessionUser(request);
		if (trainarea.getId() == null) {
			trainarea.setCuid(user.getId());
			trainarea.setCtime(new Date());
			rb = examareaService.addExamarea(trainarea);
		} else {
			trainarea.setMuid(user.getId());
			trainarea.setMtime(new Date());
			rb = examareaService.updateExamarea(trainarea);
		}
		return rb;
	}
	
	@RequestMapping(value="/list")
	public ResultBean list(Examarea trainarea) {
		return examareaService.getList(trainarea);
	}
	
	@RequestMapping(value="/listall")
	public ResultBean listall(Examarea trainarea) {
		List<Examarea> list= examareaService.getListAll(trainarea);
		ResultBean rb=new ResultBean();
		rb.setResult(list);
		return rb;
	}
	
	@RequestMapping(value="/delete")
	public ResultBean delete(Examarea trainarea) {
		return examareaService.deleteExamarea(trainarea);
	}
}

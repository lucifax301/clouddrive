package cn.com.liliyun.httpaccess.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.trainorg.model.Classinfo;
import cn.com.liliyun.trainorg.service.ClassinfoService;

@Controller
@ResponseBody
@RequestMapping(value="/class")
public class ClassinfoController extends BaseController {

	@Autowired
	private ClassinfoService classinfoService;
	
	@RequestMapping(value="/list")
	public ResultBean getList(Classinfo classinfo,HttpServletRequest request) {
		return classinfoService.selectList(classinfo);
	}
	
	@RequestMapping(value="/listall")
	public ResultBean getListAll(Classinfo classinfo) {
		return classinfoService.selectListAll(classinfo);
	}
	
	@RequestMapping(value="/delete")
	public ResultBean deleteById(Classinfo classinfo) {
		return classinfoService.deleteClass(classinfo);
	}
	
	@RequestMapping(value="/edit")
	public ResultBean addClass(Classinfo classinfo) {
		ResultBean rb;
		if (classinfo.getId() == null) {
			rb = classinfoService.addClass(classinfo);
		} else {
			rb= classinfoService.updateClass(classinfo);
		}
		return rb;
	}
	
	@RequestMapping(value="/status")
	public ResultBean status(Classinfo classinfo) {
		classinfo.setStatus((classinfo.getStatus() == 1 ? 0 : 1));
		classinfoService.updateStatus(classinfo);
		return new ResultBean();
	}
	
	
	
	@RequestMapping(value="/get")
	public ResultBean get(Classinfo classinfo) {
		return classinfoService.selectOne(classinfo);
	}
}

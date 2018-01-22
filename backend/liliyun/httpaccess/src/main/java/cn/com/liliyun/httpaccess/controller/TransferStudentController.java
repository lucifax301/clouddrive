package cn.com.liliyun.httpaccess.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.common.util.ConstantUtil;
import cn.com.liliyun.student.model.Student;
import cn.com.liliyun.student.model.TransferStudent;
import cn.com.liliyun.student.service.StudentService;
import cn.com.liliyun.user.model.User;

@Controller
@ResponseBody
public class TransferStudentController {

	@Autowired
	private StudentService studentService;
	
	//获取转店列表
	@RequestMapping(value="/transferStudent/list", method = RequestMethod.GET)
	public ResultBean getTransferList(HttpServletRequest request, TransferStudent transferStudent) {
		User user = (User) request.getSession().getAttribute(ConstantUtil.USER_SESSION);
		Boolean isChosen = false;
		if (transferStudent.getFromareaid() != null || transferStudent.getFromstoreid() != null)
			isChosen = true;
		return studentService.getTransferList(transferStudent, isChosen, user);
	}
	
	@RequestMapping(value="/transferStudent", method = RequestMethod.GET)
	public ResultBean getTransfer(HttpServletRequest request, TransferStudent transferStudent) {
		User user = (User) request.getSession().getAttribute(ConstantUtil.USER_SESSION);
		return studentService.getTransfer(transferStudent, user);
	}
	
	@RequestMapping(value="/transferStudent/add", method = RequestMethod.POST)
	public ResultBean addTransfer(HttpServletRequest request, TransferStudent transferStudent) {
		User user = (User) request.getSession().getAttribute(ConstantUtil.USER_SESSION);
		String businessid = (String) request.getSession().getAttribute(ConstantUtil.SESSION_BUSINESS);
		return studentService.addTransfer(transferStudent, user, businessid);
	}
	
	@RequestMapping(value="/transferStudent/update", method = RequestMethod.POST)
	public ResultBean editTransfer(HttpServletRequest request, TransferStudent transferStudent) {
		User user = (User) request.getSession().getAttribute(ConstantUtil.USER_SESSION);
		return studentService.editTransfer(transferStudent, user);
	}
	
	@RequestMapping(value="/transferStudent/students", method = RequestMethod.GET)
	public ResultBean getStudentList(HttpServletRequest request, Student student) {
		User user = (User) request.getSession().getAttribute(ConstantUtil.USER_SESSION);
		return studentService.getTStudentList(student, user);
	}
}

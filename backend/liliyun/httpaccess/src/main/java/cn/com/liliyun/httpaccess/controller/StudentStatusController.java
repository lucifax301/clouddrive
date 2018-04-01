package cn.com.liliyun.httpaccess.controller;

import cn.com.liliyun.student.model.StudentStatus;
import cn.com.liliyun.student.service.StudentStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.liliyun.common.model.ResultBean;


@Controller
@ResponseBody
@RequestMapping(value="/studentstatus")
public class StudentStatusController extends BaseController {

	@Autowired
	private StudentStatusService studentStatusService;

	@RequestMapping(value="/edit")
	public ResultBean edit(StudentStatus studentStatus) {
		if (studentStatus.getId() == null) {
			return studentStatusService.addStudentStatus(studentStatus);
		} else {
			return studentStatusService.updateStudentStatus(studentStatus);
		}
		
	}

	@RequestMapping(value="/list")
	public ResultBean list(StudentStatus studentStatus) {
		return studentStatusService.getList(studentStatus);
	}

	@RequestMapping(value="/delete")
	public ResultBean delete(StudentStatus studentStatus) {
		return studentStatusService.deleteStudentStatus(studentStatus);
	}

}

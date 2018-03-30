package cn.com.liliyun.httpaccess.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.liliyun.common.annotation.RequestAction;
import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.staff.model.Officer;
import cn.com.liliyun.staff.service.OfficerService;

@Controller
@ResponseBody
@RequestMapping(value="/officer")
public class OfficerController extends ExportController{

	@Autowired
	private OfficerService officerService;
	
	@RequestAction(type=RequestAction.RequestActionType.ADD)
	@RequestMapping(value="/add")
	public ResultBean add(Officer officer, HttpServletRequest request) {
		return officerService.add(officer);
	}
	
	@RequestAction(type=RequestAction.RequestActionType.UPDATE)
	@RequestMapping(value="/update")
	public ResultBean update(Officer officer, HttpServletRequest request) {
		return officerService.update(officer);
	}
	
	@RequestMapping(value="/del")
	public ResultBean del(Officer officer, HttpServletRequest request) {
		ResultBean rb= officerService.del(officer);
		return rb;
	}
	
	@RequestMapping(value="/Officer")
	public ResultBean get(Officer officer, HttpServletRequest request) {
		Officer exist= officerService.get(officer);
		return this.<Officer>buildResult(exist);
	}
	
	@RequestMapping(value="/list")
	public ResultBean getList(Officer officer, HttpServletRequest request) {
		List <Officer> list = officerService.list(officer);
		return this.<Officer>buildListResult(list);
	}
	
	@RequestMapping(value="/export")
	public ResponseEntity<byte[]> export(Officer officer, HttpServletRequest request) throws IOException {
		List <Officer> list = officerService.list(officer);
		return this.export("安全员导出数据", "安全员导出数据", "导出数据", list, Officer.class);
	}
}

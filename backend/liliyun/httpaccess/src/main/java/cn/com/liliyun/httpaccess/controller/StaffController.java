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
import cn.com.liliyun.staff.model.Staff;
import cn.com.liliyun.staff.service.StaffService;

@Controller
@ResponseBody
@RequestMapping(value="/staff")
public class StaffController extends ExportController{

	@Autowired
	private StaffService staffService;
	
	@RequestAction(type=RequestAction.RequestActionType.ADD)
	@RequestMapping(value="/add")
	public ResultBean add(Staff staff, HttpServletRequest request) {
		return staffService.add(staff);
	}
	
	@RequestAction(type=RequestAction.RequestActionType.UPDATE)
	@RequestMapping(value="/update")
	public ResultBean update(Staff staff, HttpServletRequest request) {
		return staffService.update(staff);
	}
	
	@RequestMapping(value="/del")
	public ResultBean del(Staff staff, HttpServletRequest request) {
		return staffService.del(staff);
	}
	
	@RequestMapping(value="/get")
	public ResultBean get(Staff staff, HttpServletRequest request) {
		Staff exist= staffService.get(staff);
		return this.<Staff>buildResult(exist);
			
	}
	
	@RequestMapping(value="/list")
	public ResultBean getList(Staff staff, HttpServletRequest request) {
		List <Staff> list = staffService.list(staff);
		return this.<Staff>buildListResult(list);
	}
	
	@RequestMapping(value="/export")
	public ResponseEntity<byte[]> export(Staff staff, HttpServletRequest request) throws IOException {
		List <Staff> list = staffService.list(staff);
		return this.export("员工导出数据", "员工导出数据", "导出数据", list, Staff.class);
	}
}

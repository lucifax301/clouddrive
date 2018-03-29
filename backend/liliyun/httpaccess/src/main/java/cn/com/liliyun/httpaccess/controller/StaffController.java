package cn.com.liliyun.httpaccess.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.ss.usermodel.Workbook;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.enmus.ExcelType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.liliyun.common.annotation.RequestAction;
import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.httpaccess.util.AccessWebUtil;
import cn.com.liliyun.staff.model.Staff;
import cn.com.liliyun.staff.service.StaffService;
import cn.com.liliyun.user.model.User;

import com.github.pagehelper.PageInfo;

@Controller
@ResponseBody
@RequestMapping(value="/staff")
public class StaffController {

	@Autowired
	private StaffService staffService;
	
	@RequestAction(type=RequestAction.RequestActionType.ADD)
	@RequestMapping(value="/add")
	public ResultBean add(Staff staff, HttpServletRequest request) {
		User user =  AccessWebUtil.getSessionUser(request);
		staff.setCuid(user.getId());
		ResultBean rb= staffService.add(staff);
		return rb;
	}
	
	@RequestAction(type=RequestAction.RequestActionType.UPDATE)
	@RequestMapping(value="/update")
	public ResultBean update(Staff staff, HttpServletRequest request) {
		ResultBean rb= staffService.update(staff);
		return rb;
	}
	
	@RequestMapping(value="/del")
	public ResultBean del(Staff staff, HttpServletRequest request) {
		User user =  AccessWebUtil.getSessionUser(request);
		staff.setCuid(user.getId());
		ResultBean rb= staffService.del(staff);
		return rb;
	}
	
	@RequestMapping(value="/get")
	public ResultBean get(Staff staff, HttpServletRequest request) {
		User user =  AccessWebUtil.getSessionUser(request);
		staff.setCuid(user.getId());
		try{
			Staff exist= staffService.get(staff);
			ResultBean rb = new ResultBean();
			rb.setResult(exist);
			return rb;
		}catch(Exception ex){
			ex.printStackTrace();
			ResultBean rb = new ResultBean("获取员工出错");
			return rb;
		}
	}
	
	@RequestMapping(value="/list")
	public ResultBean getList(Staff staff, HttpServletRequest request) {
		ResultBean rb = new ResultBean();
		
		List <Staff> list = staffService.list(staff);
		rb.setResult(new PageInfo<>(list));
		return rb;
	}
	
	@RequestMapping(value="/export")
	public ResponseEntity<byte[]> export(Staff staff, HttpServletRequest request) throws IOException {
		
		List <Staff> list = staffService.list(staff);
		
		ExportParams params = new ExportParams("员工导出数据", "导出数据", ExcelType.XSSF);// title
		// sheetname
		// 文件格式
		Workbook workbook = ExcelExportUtil.exportExcel(params, Staff.class,
		list);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		workbook.write(os);
		HttpHeaders headers = new HttpHeaders();
		String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		String fileName = new String(
		("员工导出数据" + time + ".xlsx").getBytes("UTF-8"), "iso-8859-1"); // 生成文件名
		headers.setContentDispositionFormData("attachment", fileName);
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		return new ResponseEntity<byte[]>(os.toByteArray(), headers,
		HttpStatus.CREATED);
	}
}

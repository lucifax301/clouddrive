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

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.httpaccess.util.AccessWebUtil;
import cn.com.liliyun.staff.model.Officer;
import cn.com.liliyun.staff.model.Staff;
import cn.com.liliyun.staff.service.OfficerService;
import cn.com.liliyun.user.model.User;

import com.github.pagehelper.PageInfo;

@Controller
@ResponseBody
@RequestMapping(value="/officer")
public class OfficerController {

	@Autowired
	private OfficerService officerService;
	
	@RequestMapping(value="/add")
	public ResultBean add(Officer officer, HttpServletRequest request) {
		User user =  AccessWebUtil.getSessionUser(request);
		officer.setCuid(user.getId());
		ResultBean rb= officerService.add(officer);
		return rb;
	}
	
	@RequestMapping(value="/update")
	public ResultBean update(Officer officer, HttpServletRequest request) {
		User user =  AccessWebUtil.getSessionUser(request);
		officer.setMuid(user.getId());
		ResultBean rb= officerService.update(officer);
		return rb;
	}
	
	@RequestMapping(value="/del")
	public ResultBean del(Officer officer, HttpServletRequest request) {
		User user =  AccessWebUtil.getSessionUser(request);
		
		ResultBean rb= officerService.del(officer);
		return rb;
	}
	
	@RequestMapping(value="/Officer")
	public ResultBean get(Officer officer, HttpServletRequest request) {
		User user =  AccessWebUtil.getSessionUser(request);
		
		Officer exist= officerService.get(officer);
		ResultBean rb = new ResultBean();
		rb.setResult(exist);
		return rb;
	}
	
	@RequestMapping(value="/list")
	public ResultBean getList(Officer officer, HttpServletRequest request) {
		User user =  AccessWebUtil.getSessionUser(request);
		
		ResultBean rb = new ResultBean();
		
		List <Officer> list = officerService.list(officer);
		rb.setResult(new PageInfo<>(list));
		return rb;
	}
	
	@RequestMapping(value="/export")
	public ResponseEntity<byte[]> export(Officer officer, HttpServletRequest request) throws IOException {
		User user =  AccessWebUtil.getSessionUser(request);
		
		List <Officer> list = officerService.list(officer);
		
		ExportParams params = new ExportParams("安全员导出数据", "导出数据", ExcelType.XSSF);// title
		// sheetname
		// 文件格式
		Workbook workbook = ExcelExportUtil.exportExcel(params, Officer.class,
		list);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		workbook.write(os);
		HttpHeaders headers = new HttpHeaders();
		String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		String fileName = new String(
		("安全员导出数据" + time + ".xlsx").getBytes("UTF-8"), "iso-8859-1"); // 生成文件名
		headers.setContentDispositionFormData("attachment", fileName);
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		return new ResponseEntity<byte[]>(os.toByteArray(), headers,
		HttpStatus.CREATED);
	}
}

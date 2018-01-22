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
import cn.com.liliyun.staff.model.Assessor;
import cn.com.liliyun.staff.service.AssessorService;
import cn.com.liliyun.user.model.User;

import com.github.pagehelper.PageInfo;

@Controller
@ResponseBody
@RequestMapping(value="/assessor")
public class AssessorController {

	@Autowired
	private AssessorService assessorService;
	
	@RequestMapping(value="/add")
	public ResultBean add(Assessor assessor, HttpServletRequest request) {
		User user =  AccessWebUtil.getSessionUser(request);
		assessor.setCuid(user.getId());
		ResultBean rb= assessorService.add(assessor);
		return rb;
	}
	
	@RequestMapping(value="/update")
	public ResultBean update(Assessor assessor, HttpServletRequest request) {
		User user =  AccessWebUtil.getSessionUser(request);
		assessor.setMuid(user.getId());
		ResultBean rb= assessorService.update(assessor);
		return rb;
	}
	
	@RequestMapping(value="/del")
	public ResultBean del(Assessor assessor, HttpServletRequest request) {
		User user =  AccessWebUtil.getSessionUser(request);
		
		ResultBean rb= assessorService.del(assessor);
		return rb;
	}
	
	@RequestMapping(value="/Officer")
	public ResultBean get(Assessor assessor, HttpServletRequest request) {
		User user =  AccessWebUtil.getSessionUser(request);
		
		Assessor exist= assessorService.get(assessor);
		ResultBean rb = new ResultBean();
		rb.setResult(exist);
		return rb;
	}
	
	@RequestMapping(value="/list")
	public ResultBean getList(Assessor assessor, HttpServletRequest request) {
		User user =  AccessWebUtil.getSessionUser(request);
		
		ResultBean rb = new ResultBean();
		
		List <Assessor> list = assessorService.list(assessor);
		rb.setResult(new PageInfo<>(list));
		return rb;
	}
	
	@RequestMapping(value="/export")
	public ResponseEntity<byte[]> export(Assessor assessor, HttpServletRequest request) throws IOException {
		User user =  AccessWebUtil.getSessionUser(request);
		
		List <Assessor> list = assessorService.list(assessor);
		
		ExportParams params = new ExportParams("审核员导出数据", "导出数据", ExcelType.XSSF);// title
		// sheetname
		// 文件格式
		Workbook workbook = ExcelExportUtil.exportExcel(params, Assessor.class,
		list);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		workbook.write(os);
		HttpHeaders headers = new HttpHeaders();
		String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		String fileName = new String(
		("审核员导出数据" + time + ".xlsx").getBytes("UTF-8"), "iso-8859-1"); // 生成文件名
		headers.setContentDispositionFormData("attachment", fileName);
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		return new ResponseEntity<byte[]>(os.toByteArray(), headers,
		HttpStatus.CREATED);
	}
}

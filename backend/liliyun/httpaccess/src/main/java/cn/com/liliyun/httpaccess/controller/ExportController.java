package cn.com.liliyun.httpaccess.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.enmus.ExcelType;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;



public class ExportController extends BaseController {

	public <T> ResponseEntity<byte[]>  export(String fileName,String title, String sheetName,List<T> list,Class<T> cls)
	throws IOException{
		ExportParams params = new ExportParams(title, sheetName, ExcelType.XSSF);// title
		// sheetname
		// 文件格式
		
		Workbook workbook = ExcelExportUtil.exportExcel(params, cls,
		list);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		workbook.write(os);
		HttpHeaders headers = new HttpHeaders();
		String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		String newfileName = new String(
		(fileName + time + ".xlsx").getBytes("UTF-8"), "iso-8859-1"); // 生成文件名
		headers.setContentDispositionFormData("attachment", newfileName);
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		return new ResponseEntity<byte[]>(os.toByteArray(), headers,
		HttpStatus.CREATED);
	}
	
	public <T> ResponseEntity<byte[]>  export(String fileName,Workbook workbook)
			throws IOException{
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		workbook.write(os);
		HttpHeaders headers = new HttpHeaders();
		String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		String newfileName = new String(
		(fileName + time + ".xlsx").getBytes("UTF-8"), "iso-8859-1"); // 生成文件名
		headers.setContentDispositionFormData("attachment", newfileName);
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		return new ResponseEntity<byte[]>(os.toByteArray(), headers,
		HttpStatus.CREATED);
	}
}

package cn.com.liliyun.httpaccess.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.enmus.ExcelType;
import org.jeecgframework.poi.exception.excel.ExcelImportException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.trainorg.model.OrderExam;
import cn.com.liliyun.trainorg.model.OrderExamItem;
import cn.com.liliyun.trainorg.service.OrderExamService;


/**
 * 学员考试预约管理
 */
@Controller
@ResponseBody
@RequestMapping(value="/orderexam")
public class OrderExamController extends BaseController {
	
	@Autowired
	private OrderExamService orderExamService;
	
	@RequestMapping(value="/list")
	public ResultBean list(OrderExam orderExam) {
		ResultBean rb = new ResultBean();
		List <OrderExam> list = orderExamService.list(orderExam);
		rb.setResult(new PageInfo<>(list));
		return rb;
	}
	
	@RequestMapping(value="/itemlist")
	public ResultBean itemList(OrderExamItem orderExamItem) {
		ResultBean rb = new ResultBean();
		List <OrderExamItem> list = orderExamService.listItem(orderExamItem);
		rb.setResult(new PageInfo<>(list));
		return rb;
	}

	@RequestMapping(value="/get")
	public ResultBean get(OrderExamItem orderExamItem) {
		ResultBean rb = new ResultBean();
		orderExamItem = orderExamService.selectOne(orderExamItem);
		rb.setResult(orderExamItem);
		return rb;
	}
	
	@RequestMapping(value="/listOfCoach")
	public ResultBean listOfCoach(OrderExamItem orderExam) {
		ResultBean rb = new ResultBean();
		List <OrderExamItem> list = orderExamService.listOfCoach(orderExam);
		rb.setResult(new PageInfo<>(list));
		return rb;
	}
	
	@RequestMapping(value="/add")
	public ResultBean add(HttpServletRequest request, String json) {
		List <OrderExamItem> list = JSONObject.parseArray(json, OrderExamItem.class);
		return orderExamService.add(list);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value="/import",method=RequestMethod.POST)
	public ResultBean importOrderexam(@RequestParam("file") MultipartFile file,
			HttpServletRequest request) throws Exception {
		ResultBean rb = new ResultBean();
		List <OrderExamItem> list = null;
		try {
			list = ExcelImportUtil.importExcel(file.getInputStream(), OrderExamItem.class, new ImportParams());
		} catch (ExcelImportException e) {
			rb.setCode(100);
			rb.setMsg("数据解析错误,请检查导入数据模板!");
			return rb;
		}
		Map<String, Object> rtnData =  orderExamService.importData(list);
		list = (List<OrderExamItem>) rtnData.get("errorlist");
		if (list != null && list.size() > 0) {
			ExportParams eparams = new ExportParams("预约考试导入错误数据", "错误数据", ExcelType.XSSF);//title sheetname 文件格式
	    	Workbook workbook = ExcelExportUtil.exportExcel(eparams, OrderExamItem.class, list);
	    	ByteArrayOutputStream os = new ByteArrayOutputStream();
	    	workbook.write(os);
	        String time = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
	        String fileName = "预约考试导入错误数据" + time + ".xlsx"; //生成文件名
	        String path = request.getSession().getServletContext().getRealPath("WEB-INF") + "/errorExcel/";
	        FileUtils.writeByteArrayToFile(new File(path + fileName), os.toByteArray());
	    	rtnData.put("filename", fileName);
		}
		rb.setResult(rtnData);
		return rb;
	}
	
	@RequestMapping(value="/export")
	public ResponseEntity<byte[]> export(OrderExam orderExam) throws IOException {
		orderExam.setPageNo(-1);
	  	List<OrderExam> list = orderExamService.list(orderExam);
		ExportParams params = new ExportParams("导出数据", "导出数据", ExcelType.XSSF);//title sheetname 文件格式
		Workbook workbook = ExcelExportUtil.exportExcel(params, OrderExam.class, list);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		workbook.write(os);
	    HttpHeaders headers = new HttpHeaders();    
	    String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	    String fileName = new String(("导出数据" + time + ".xlsx").getBytes("UTF-8"),"iso-8859-1"); //生成文件名
	    headers.setContentDispositionFormData("attachment", fileName);   
	    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);   
	    return new ResponseEntity<byte[]>(os.toByteArray(), headers, HttpStatus.CREATED);
	}
	
}

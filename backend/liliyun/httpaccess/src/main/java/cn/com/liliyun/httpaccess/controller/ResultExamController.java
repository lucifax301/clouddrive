package cn.com.liliyun.httpaccess.controller;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.trainorg.model.ResultExam;
import cn.com.liliyun.trainorg.model.ResultExamItem;
import cn.com.liliyun.trainorg.service.ResultExamService;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
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

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 	学员考试成绩管理
 */
@Controller
@ResponseBody
@RequestMapping(value="/resultexam")
public class ResultExamController extends BaseController {
	
	@Autowired
	private ResultExamService resultExamService;
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public ResultBean list(HttpServletRequest request,ResultExam resultExam) {
		ResultBean rb = new ResultBean();
		List <ResultExam> list = resultExamService.list(resultExam);
		rb.setResult(new PageInfo<>(list));
		return rb;
	}
	
	@RequestMapping(value="/itemlist",method=RequestMethod.GET)
	public ResultBean itemList(HttpServletRequest request,ResultExamItem resultExamItem) {
		ResultBean rb = new ResultBean();
		List <ResultExamItem> list = resultExamService.listItem(resultExamItem);
		rb.setResult(new PageInfo<>(list));
		return rb;
	}
	
	@RequestMapping(value="/listOfCoach",method=RequestMethod.GET)
	public ResultBean listOfCoach(HttpServletRequest request,ResultExamItem resultExam) {
		ResultBean rb = new ResultBean();
		List <ResultExamItem> list = resultExamService.listOfCoach(resultExam);
		rb.setResult(new PageInfo<>(list));
		return rb;
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public ResultBean add(HttpServletRequest request,String json) {
		List <ResultExamItem> list = JSONObject.parseArray(json, ResultExamItem.class);
		return resultExamService.add(list);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/import",method=RequestMethod.POST)
	public ResultBean importResultExam(@RequestParam("file") MultipartFile file,
			HttpServletRequest request) throws Exception {
		ResultBean rb = new ResultBean();
		List <ResultExamItem> list = null;
		try {
			list = ExcelImportUtil.importExcel(file.getInputStream(), ResultExamItem.class, new ImportParams());
		} catch (ExcelImportException e) {
			rb.setCode(100);
			rb.setMsg("数据解析错误,请检查导入数据模板!");
			return rb;
		}
		Map<String, Object> rtnData =  resultExamService.importData(list);
		list = (List<ResultExamItem>) rtnData.get("errorlist");
			if (list != null && list.size() > 0) {
			ExportParams eparams = new ExportParams("考试成绩导入错误数据", "错误数据", ExcelType.XSSF);//title sheetname 文件格式
			Workbook workbook = ExcelExportUtil.exportExcel(eparams, ResultExamItem.class, list);
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			workbook.write(os);
			String time = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
			String fileName = "考试成绩导入错误数据" + time + ".xlsx"; //生成文件名
			String path = request.getSession().getServletContext().getRealPath("WEB-INF") + "/errorExcel/";
			FileUtils.writeByteArrayToFile(new File(path + fileName), os.toByteArray());
			rtnData.put("filename", fileName);
		}
		rb.setResult(rtnData);
		return rb;
	}
	
	@RequestMapping(value="/export",method=RequestMethod.GET)
	public ResponseEntity<byte[]> export(ResultExam resultExam) throws IOException {
		resultExam.setPageNo(-1);
	  	List<ResultExam> list = resultExamService.list(resultExam);
		ExportParams params = new ExportParams("导出数据", "导出数据", ExcelType.XSSF);//title sheetname 文件格式
		Workbook workbook = ExcelExportUtil.exportExcel(params, ResultExam.class, list);
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

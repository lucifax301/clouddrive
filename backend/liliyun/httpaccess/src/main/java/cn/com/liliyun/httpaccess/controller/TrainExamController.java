package cn.com.liliyun.httpaccess.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
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
import cn.com.liliyun.trainorg.model.TrainExam;
import cn.com.liliyun.trainorg.model.TrainExamItem;
import cn.com.liliyun.trainorg.service.TrainExamService;


/**
 * 学员考试培训管理
 */
@Controller
@ResponseBody
@RequestMapping(value="/trainexam")
public class TrainExamController extends BaseController {
	
	@Autowired
	private TrainExamService trainExamService;
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public ResultBean list(HttpServletRequest request,TrainExam trainExam) {
		ResultBean rb = new ResultBean();
		List <TrainExam> list = trainExamService.list(trainExam);
		rb.setResult(new PageInfo<>(list));
		return rb;
	}
	
	@RequestMapping(value="/itemlist",method=RequestMethod.GET)
	public ResultBean itemList(HttpServletRequest request,TrainExamItem trainExamItem) {
		ResultBean rb = new ResultBean();
		List <TrainExamItem> list = trainExamService.listItem(trainExamItem);
		rb.setResult(new PageInfo<>(list));
		return rb;
	}
	
	@RequestMapping(value="/listOfCoach",method=RequestMethod.GET)
	public ResultBean listOfCoach(HttpServletRequest request,TrainExamItem trainExam) {
		ResultBean rb = new ResultBean();
		List <TrainExamItem> list = trainExamService.listOfCoach(trainExam);
		rb.setResult(new PageInfo<>(list));
		return rb;
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public ResultBean add(HttpServletRequest request,String json) {
		List <TrainExamItem> list = JSONObject.parseArray(json, TrainExamItem.class);
		return trainExamService.add(list);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/import",method=RequestMethod.POST)
	public ResultBean importTrainExam(@RequestParam("file") MultipartFile file,
			HttpServletRequest request) throws Exception {
		ResultBean rb = new ResultBean();
		List <TrainExamItem> list = null;
		try {
			list = ExcelImportUtil.importExcel(file.getInputStream(), TrainExamItem.class, new ImportParams());
		} catch (ExcelImportException e) {
			rb.setCode(100);
			rb.setMsg("数据解析错误,请检查导入数据模板!");
			return rb;
		}
		Map<String, Object> rtnData =  trainExamService.importData(list);
		list = (List<TrainExamItem>) rtnData.get("errorlist");
		if (list != null && list.size() > 0) {
			ExportParams eparams = new ExportParams("考试培训导入错误数据", "错误数据", ExcelType.XSSF);//title sheetname 文件格式
	    	Workbook workbook = ExcelExportUtil.exportExcel(eparams, TrainExamItem.class, list);
	    	ByteArrayOutputStream os = new ByteArrayOutputStream();
	    	workbook.write(os);
	        String time = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
	        String fileName = "考试培训导入错误数据" + time + ".xlsx"; //生成文件名
	        String path = request.getSession().getServletContext().getRealPath("WEB-INF") + "/errorExcel/";
	        FileUtils.writeByteArrayToFile(new File(path + fileName), os.toByteArray());
	    	rtnData.put("filename", fileName);
		}
		rb.setResult(rtnData);
		return rb;
	}
	
	@RequestMapping(value="/export",method=RequestMethod.GET)
	public ResponseEntity<byte[]> export(TrainExam trainExam) throws IOException {
		trainExam.setPageNo(-1);
	  	List<TrainExam> list = trainExamService.list(trainExam);
		ExportParams params = new ExportParams("导出数据", "导出数据", ExcelType.XSSF);//title sheetname 文件格式
		Workbook workbook = ExcelExportUtil.exportExcel(params, TrainExam.class, list);
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

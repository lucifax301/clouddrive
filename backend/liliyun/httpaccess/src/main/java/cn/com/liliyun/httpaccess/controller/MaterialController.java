package cn.com.liliyun.httpaccess.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.student.model.Material;
import cn.com.liliyun.student.model.MaterialItem;
import cn.com.liliyun.student.service.MaterialService;


/**
 * 资料管理
 */
@Controller
@ResponseBody
@RequestMapping(value="/material")
public class MaterialController extends BaseController {
	
	@Autowired
	private MaterialService materialService;
	
	@RequestMapping(value="/list")
	public ResultBean getList(Material material) {
		ResultBean rb = new ResultBean();
		List <Material> list = materialService.list(material);
		rb.setResult(new PageInfo<>(list));
		return rb;
	}
	
	@RequestMapping(value="/itemlist")
	public ResultBean itemList(MaterialItem materialItem) {
		ResultBean rb = new ResultBean();
		List <MaterialItem> list = materialService.listItem(materialItem);
		rb.setResult(new PageInfo<>(list));
		return rb;
	}
	
	@RequestMapping(value="/learncard")
	public ResultBean learncard(HttpServletRequest request, String json) {
		List <MaterialItem> list = JSONObject.parseArray(json, MaterialItem.class);
		return materialService.doLearncard(getUser(request), list);
	}
	
	@RequestMapping(value="/applystamp")
	public ResultBean applystamp(HttpServletRequest request, String json) {
		List <MaterialItem> list = JSONObject.parseArray(json, MaterialItem.class);
		return materialService.doApplyStamp(getUser(request), list);
	}
	
	@RequestMapping(value="/schoolstamp")
	public ResultBean schoolstamp(HttpServletRequest request, String json) {
		List <MaterialItem> list = JSONObject.parseArray(json, MaterialItem.class);
		return materialService.doSchoolStamp(getUser(request), list);
	}
	
	@RequestMapping(value="/license")
	public ResultBean license(HttpServletRequest request, String json) {
		List <MaterialItem> list = JSONObject.parseArray(json, MaterialItem.class);
		return materialService.doLicense(getUser(request), list);
	}
	
	@RequestMapping(value="/tribillprint")
	public ResultBean tribillprint(HttpServletRequest request, String json) {
		List <MaterialItem> list = JSONObject.parseArray(json, MaterialItem.class);
		return materialService.doTribillPrint(getUser(request), list);
	}
	
	@RequestMapping(value="/tribillstamp")
	public ResultBean tribillstamp(HttpServletRequest request, String json) {
		List <MaterialItem> list = JSONObject.parseArray(json, MaterialItem.class);
		return materialService.doTribillStamp(getUser(request), list);
	}
	
	@RequestMapping(value="/repaymaterial")
	public ResultBean repaymaterial(HttpServletRequest request, String json) {
		List <MaterialItem> list = JSONObject.parseArray(json, MaterialItem.class);
		return materialService.doRepayMaterial(getUser(request), list);
	}
	
	@RequestMapping(value="/rtnmaterial")
	public ResultBean rtnmaterial(HttpServletRequest request, String json) {
		List <MaterialItem> list = JSONObject.parseArray(json, MaterialItem.class);
		return materialService.doRtnMaterial(getUser(request), list);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/importiccard",method=RequestMethod.POST)
	public ResultBean importiccard(@RequestParam("file") MultipartFile file,
			HttpServletRequest request) throws Exception {
		ResultBean rb = new ResultBean();
		List <MaterialItem> list;
		try {
			list = ExcelImportUtil.importExcel(file.getInputStream(), MaterialItem.class, new ImportParams());
		} catch (ExcelImportException e) {
			rb.setCode(100);
			rb.setMsg("数据解析错误,请检查导入数据模板!");
			return rb;
		}
		Map<String, Object> rtnData = materialService.importIcCard(getUser(request),list);
		list = (List<MaterialItem>) rtnData.get("errorlist");
		if (list != null && list.size() > 0) {
			ExportParams eparams = new ExportParams("IC卡导入错误数据", "错误数据", ExcelType.XSSF);//title sheetname 文件格式
	    	Workbook workbook = ExcelExportUtil.exportExcel(eparams, MaterialItem.class, list);
	    	ByteArrayOutputStream os = new ByteArrayOutputStream();
	    	workbook.write(os);
	        String time = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
	        String fileName = "IC卡导入错误数据" + time + ".xlsx"; //生成文件名
	        String path = request.getSession().getServletContext().getRealPath("WEB-INF") + "/errorExcel/";
	        FileUtils.writeByteArrayToFile(new File(path + fileName), os.toByteArray());
	    	rtnData.put("filename", fileName);
		}
		rb.setResult(rtnData);
		return rb;
	}
	
}

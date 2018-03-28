package cn.com.liliyun.httpaccess.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.student.model.File;
import cn.com.liliyun.student.model.FileItem;
import cn.com.liliyun.student.service.FileService;


/**
 * 档案管理
 */
@Controller
@ResponseBody
@RequestMapping(value="/file")
public class FileController extends BaseController {
	
	@Autowired
	private FileService fileService;
	
	@RequestMapping(value="/list")
	public ResultBean getList(HttpServletRequest request, File file) {
		ResultBean rb = new ResultBean();
		List <File> list = fileService.list(file);
		rb.setResult(new PageInfo<>(list));
		return rb;
	}
	
	@RequestMapping(value="/itemlist")
	public ResultBean itemlist(HttpServletRequest request,FileItem fileItem) {
		ResultBean rb = new ResultBean();
		List <FileItem> list = fileService.listItem(fileItem);
		rb.setResult(new PageInfo<>(list));
		return rb; 
	}
	
	@RequestMapping(value="/handlefile")
	public ResultBean handleFile(HttpServletRequest request,String json) {
		List <FileItem> list = JSONObject.parseArray(json, FileItem.class);
		return fileService.doHandleFile(list);
	}
	
	@RequestMapping(value="/stufile")
	public ResultBean studentFile(HttpServletRequest request, String json) {
		List <FileItem> list = JSONObject.parseArray(json, FileItem.class);
		return fileService.doStuFile(list);
	}
	
}
package cn.com.liliyun.student.service;

import java.util.List;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.student.model.File;
import cn.com.liliyun.student.model.FileItem;
import cn.com.liliyun.user.model.User;

public interface FileService {

	public List<File> list(File file);
	
	public ResultBean doHandleFile(List <FileItem> list);
	
	public List <FileItem> listItem(FileItem fileItem);
	
	public ResultBean doStuFile(List <FileItem> list);
	
}

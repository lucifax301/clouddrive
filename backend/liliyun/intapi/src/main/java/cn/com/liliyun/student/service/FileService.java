package cn.com.liliyun.student.service;

import java.util.List;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.student.model.File;
import cn.com.liliyun.student.model.FileItem;

public interface FileService {

	List<File> list(File file);
	
	ResultBean doHandleFile(List <FileItem> list);
	
	List <FileItem> listItem(FileItem fileItem);
	
	ResultBean doStuFile(List <FileItem> list);
	
}

package cn.com.liliyun.student.service;

import java.util.List;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.student.model.File;
import cn.com.liliyun.student.model.FileItem;
import cn.com.liliyun.user.model.User;

public interface FileService {

	public List<File> list(User user,File file);
	
	public ResultBean doHandleFile(User user, List <FileItem> list);
	
	public List <FileItem> listItem(User user,FileItem fileItem);
	
	public ResultBean doStuFile(User user, List <FileItem> list);
	
}

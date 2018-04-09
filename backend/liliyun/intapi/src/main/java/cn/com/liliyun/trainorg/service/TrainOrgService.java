package cn.com.liliyun.trainorg.service;

import java.util.List;
import java.util.Map;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.trainorg.model.Personnel;
/**
 * 
 * 培训机构：人员管理
 * @author Administrator
 *
 */
public interface TrainOrgService {
	
	ResultBean addPersonnel(Personnel personnel);
	
	ResultBean deletePersonnel(Personnel personnel);
	
	ResultBean updatePersonnel(Personnel personnel);
	
	ResultBean getPersonnelByers(Personnel personnel);
	
	ResultBean getPersonnelList(Personnel personnel);
	
	Personnel getPersonnelByid(Personnel personnel);
	
	//void addUser(User u);
	
	//List <User> getAll(PageModel pageModel);
	Map getExcelData(String path);
	
	void improtExcel(List list);
	
}

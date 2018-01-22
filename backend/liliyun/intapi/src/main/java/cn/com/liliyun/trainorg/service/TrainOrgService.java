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
	
	public ResultBean addPersonnel(Personnel personnel);
	
	public ResultBean deletePersonnel(Personnel personnel);
	
	public ResultBean updatePersonnel(Personnel personnel);
	
	public ResultBean getPersonnelByers(Personnel personnel);
	
	public ResultBean getPersonnelList(Personnel personnel);
	
	public Personnel getPersonnelByid(Personnel personnel);
	
	//void addUser(User u);
	
	//List <User> getAll(PageModel pageModel);
	public Map getExcelData(String path);
	
	public void improtExcel(List list);
	
}

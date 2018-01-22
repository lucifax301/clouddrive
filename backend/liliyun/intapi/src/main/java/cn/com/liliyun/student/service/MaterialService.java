package cn.com.liliyun.student.service;

import java.util.List;
import java.util.Map;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.student.model.Material;
import cn.com.liliyun.student.model.MaterialItem;
import cn.com.liliyun.user.model.User;

public interface MaterialService {

	public List <Material> list(Material material);
	
	public List <MaterialItem> listItem(MaterialItem materialItem);
	
	public ResultBean doLearncard(User user,List <MaterialItem> list);
	
	public ResultBean doApplyStamp(User user,List <MaterialItem> list);
	
	public ResultBean doSchoolStamp(User user,List <MaterialItem> list);
	
	public ResultBean doTribillStamp(User user,List <MaterialItem> list);
	
	public ResultBean doTribillPrint(User user,List <MaterialItem> list);
	
	public ResultBean doRepayMaterial(User user,List <MaterialItem> list);
	
	public ResultBean doRtnMaterial(User user,List <MaterialItem> list);
	
	public ResultBean doLicense(User user,List <MaterialItem> list);
	
	public Map<String, Object> importIcCard(User user,List <MaterialItem> list);
}

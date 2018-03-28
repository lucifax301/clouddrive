package cn.com.liliyun.student.service;

import java.util.List;
import java.util.Map;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.student.model.Material;
import cn.com.liliyun.student.model.MaterialItem;

public interface MaterialService {

	public List <Material> list(Material material);
	
	public List <MaterialItem> listItem(MaterialItem materialItem);
	
	public ResultBean doLearncard(List <MaterialItem> list);
	
	public ResultBean doApplyStamp(List <MaterialItem> list);
	
	public ResultBean doSchoolStamp(List <MaterialItem> list);
	
	public ResultBean doTribillStamp(List <MaterialItem> list);
	
	public ResultBean doTribillPrint(List <MaterialItem> list);
	
	public ResultBean doRepayMaterial(List <MaterialItem> list);
	
	public ResultBean doRtnMaterial(List <MaterialItem> list);
	
	public ResultBean doLicense(List <MaterialItem> list);
	
	public Map<String, Object> importIcCard(List <MaterialItem> list);
}

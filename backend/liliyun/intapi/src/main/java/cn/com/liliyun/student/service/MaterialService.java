package cn.com.liliyun.student.service;

import java.util.List;
import java.util.Map;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.student.model.Material;
import cn.com.liliyun.student.model.MaterialItem;

public interface MaterialService {

	List <Material> list(Material material);
	
	List <MaterialItem> listItem(MaterialItem materialItem);
	
	ResultBean doLearncard(List <MaterialItem> list);
	
	ResultBean doApplyStamp(List <MaterialItem> list);
	
	ResultBean doSchoolStamp(List <MaterialItem> list);
	
	ResultBean doTribillStamp(List <MaterialItem> list);
	
	ResultBean doTribillPrint(List <MaterialItem> list);
	
	ResultBean doRepayMaterial(List <MaterialItem> list);
	
	ResultBean doRtnMaterial(List <MaterialItem> list);
	
	ResultBean doLicense(List <MaterialItem> list);
	
	Map<String, Object> importIcCard(List <MaterialItem> list);
}

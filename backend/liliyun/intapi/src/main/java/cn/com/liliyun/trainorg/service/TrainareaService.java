package cn.com.liliyun.trainorg.service;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.trainorg.model.Trainarea;
/**
 * 
 * 培训机构：教学区域管理
 * @author Administrator
 *
 */
public interface TrainareaService {
	
	ResultBean addTrainarea(Trainarea trainarea);
	
	ResultBean deleteTrainarea(Trainarea trainarea);
	
	ResultBean updateTrainarea(Trainarea trainarea);
	
	ResultBean getList(Trainarea trainarea);
	
	ResultBean getTrainareaById(Trainarea trainarea);
	
	Integer getCount(Trainarea trainarea);
	
}

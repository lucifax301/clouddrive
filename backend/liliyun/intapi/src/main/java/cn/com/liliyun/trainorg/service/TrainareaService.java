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
	
	public ResultBean addTrainarea(Trainarea trainarea);
	
	public ResultBean deleteTrainarea(Trainarea trainarea);
	
	public ResultBean updateTrainarea(Trainarea trainarea);
	
	public ResultBean getList(Trainarea trainarea);
	
	public ResultBean getTrainareaById(Trainarea trainarea);
	
	public Integer getCount(Trainarea trainarea);
	
}

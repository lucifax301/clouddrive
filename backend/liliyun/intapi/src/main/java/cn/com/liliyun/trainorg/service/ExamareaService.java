package cn.com.liliyun.trainorg.service;

import java.util.List;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.trainorg.model.Examarea;

public interface ExamareaService {

	public ResultBean addExamarea(Examarea trainarea);
	
	public ResultBean deleteExamarea(Examarea trainarea);
	
	public ResultBean updateExamarea(Examarea trainarea);
	
	public ResultBean getList(Examarea trainarea);
	
	public List<Examarea> getListAll(Examarea trainarea);
	
	public ResultBean getExamareaById(Examarea trainarea);
}

package cn.com.liliyun.trainorg.service;

import java.util.List;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.trainorg.model.Examarea;

public interface ExamareaService {

	ResultBean addExamarea(Examarea trainarea);
	
	ResultBean deleteExamarea(Examarea trainarea);
	
	ResultBean updateExamarea(Examarea trainarea);
	
	ResultBean getList(Examarea trainarea);
	
	List<Examarea> getListAll(Examarea trainarea);
	
	ResultBean getExamareaById(Examarea trainarea);
}

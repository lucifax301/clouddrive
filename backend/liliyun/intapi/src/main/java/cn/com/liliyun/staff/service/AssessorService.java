package cn.com.liliyun.staff.service;

import java.util.List;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.staff.model.Assessor;


public interface AssessorService {

	List<Assessor> list(Assessor assessor);
	
	ResultBean add(Assessor assessor);
	
	ResultBean del(Assessor assessor);
	
	ResultBean update(Assessor assessor);
	
	Assessor get(Assessor assessor);
}

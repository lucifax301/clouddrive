package cn.com.liliyun.staff.service;

import java.util.List;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.staff.model.Assessor;
import cn.com.liliyun.staff.model.Officer;


public interface AssessorService {

	public List<Assessor> list(Assessor assessor);
	
	public ResultBean add(Assessor assessor);
	
	public ResultBean del(Assessor assessor);
	
	public ResultBean update(Assessor assessor);
	
	public Assessor get(Assessor assessor);
}

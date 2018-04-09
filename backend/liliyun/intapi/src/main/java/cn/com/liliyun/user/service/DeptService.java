package cn.com.liliyun.user.service;

import java.util.List;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.user.model.Dept;

public interface DeptService {

	ResultBean addDept(Dept dept);
	
	ResultBean updateDept(Dept dept);
	
	List<Dept> selectList(Dept dept);
	
	List<Dept> selectAllList(Dept dept);
	
	ResultBean delDept(Dept dept);
}

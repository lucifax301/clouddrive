package cn.com.liliyun.user.service;

import java.util.List;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.user.model.Dept;

public interface DeptService {

	public ResultBean addDept(Dept dept);
	
	public ResultBean updateDept(Dept dept);
	
	public List<Dept> selectList(Dept dept);
	
	public List<Dept> selectAllList(Dept dept);
	
	public ResultBean delDept(Dept dept);
}

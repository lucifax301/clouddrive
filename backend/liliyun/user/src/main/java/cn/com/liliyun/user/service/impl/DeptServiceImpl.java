package cn.com.liliyun.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.common.util.PageUtil;
import cn.com.liliyun.user.mapper.DeptMapper;
import cn.com.liliyun.user.model.Dept;
import cn.com.liliyun.user.service.DeptService;

@Service
public class DeptServiceImpl implements DeptService {

	@Autowired
	private DeptMapper deptMapper;
	
	@Override
	public ResultBean addDept(Dept dept) {
		deptMapper.insert(dept);
		return new ResultBean();	
	}

	@Override
	public List<Dept> selectList(Dept dept) {
		PageUtil.startPage(dept);
		return deptMapper.selectList(dept);
	}
	
	@Override
	public List<Dept> selectAllList(Dept dept) {
		return deptMapper.selectList(dept);
	}

	@Override
	public ResultBean delDept(Dept dept) {
		deptMapper.delete(dept);
		return new ResultBean();	
	}

	@Override
	public ResultBean updateDept(Dept dept) {
		deptMapper.update(dept);
		return new ResultBean();
	}

	
}

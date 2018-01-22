package cn.com.liliyun.staff.service;

import java.util.List;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.staff.model.Officer;


public interface OfficerService {

	public List<Officer> list(Officer officer);
	
	public ResultBean add(Officer officer);
	
	public ResultBean del(Officer officer);
	
	public ResultBean update(Officer officer);
	
	public Officer get(Officer officer);
}

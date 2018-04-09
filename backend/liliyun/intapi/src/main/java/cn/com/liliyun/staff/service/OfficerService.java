package cn.com.liliyun.staff.service;

import java.util.List;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.staff.model.Officer;


public interface OfficerService {

	List<Officer> list(Officer officer);
	
	ResultBean add(Officer officer);
	
	ResultBean del(Officer officer);
	
	ResultBean update(Officer officer);
	
	Officer get(Officer officer);
}

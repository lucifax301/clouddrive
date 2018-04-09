package cn.com.liliyun.staff.service;

import java.util.List;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.staff.model.Staff;
import cn.com.liliyun.staff.vo.StaffVo;

public interface StaffService {

	List<Staff> list(Staff staff);
	
	ResultBean add(Staff staff);
	
	ResultBean del(Staff staff);
	
	ResultBean update(Staff staff);
	
	ResultBean updateByPrimaryKeySelective(Staff staff);
	
	Staff get(Staff staff);
	
	StaffVo getStaffVo(Staff staff) ;

	List<Staff> getAppStafflist(Staff staff);
}

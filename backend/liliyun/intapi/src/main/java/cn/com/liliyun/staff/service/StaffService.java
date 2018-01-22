package cn.com.liliyun.staff.service;

import java.util.List;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.staff.model.Staff;
import cn.com.liliyun.staff.vo.StaffVo;

public interface StaffService {

	public List<Staff> list(Staff staff);
	
	public ResultBean add(Staff staff);
	
	public ResultBean del(Staff staff);
	
	public ResultBean update(Staff staff);
	
	public ResultBean updateByPrimaryKeySelective(Staff staff);
	
	public Staff get(Staff staff) throws Exception;
	
	public StaffVo getStaffVo(Staff staff) throws Exception;

	public List<Staff> getAppStafflist(Staff staff);
}

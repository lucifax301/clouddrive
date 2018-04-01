package cn.com.liliyun.staff.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.json.JSON;
import com.alibaba.dubbo.common.json.ParseException;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.common.util.PageUtil;
import cn.com.liliyun.staff.mapper.StaffMapper;
import cn.com.liliyun.staff.model.Staff;
import cn.com.liliyun.staff.service.StaffService;
import cn.com.liliyun.staff.vo.StaffVo;

@Service
public class StaffServiceImpl implements StaffService {

	@Autowired
	private StaffMapper staffMapper;
	
	@Override
	public List<Staff> list(Staff staff) {
		PageUtil.startPage(staff);
		return staffMapper.selectByCondition(staff);
	}

	@Override
	public ResultBean add(Staff staff) {
		Staff p=new Staff();
		p.setMobile(staff.getMobile());
		p.setDblink(staff.getDblink());
		p.setIsdel(0);
		List listm = staffMapper.selectByCondition(p);
		if (listm.size() > 0) {
			ResultBean rb=new ResultBean("手机号已存在!");
			return rb;
		}
		
		staffMapper.insert(staff);
		return new ResultBean();
	}

	@Override
	public ResultBean del(Staff staff){
		staffMapper.delete(staff);
		return new ResultBean();
	}
	
	@Override
	public ResultBean update(Staff staff){
		staffMapper.update(staff);
		return new ResultBean();
	}
	
	@Override
	public ResultBean updateByPrimaryKeySelective(Staff staff){
		staffMapper.updateByPrimaryKeySelective(staff);
		return new ResultBean();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Staff get(Staff staff){
		try{
			Staff exist=staffMapper.selectByPrimaryKey(staff);
			Map detailinf = JSON.parse(exist.getDetail(), Map.class);
			
			exist.setDetailinfo(detailinf);
			return exist;
		}catch(ParseException e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Staff> getAppStafflist(Staff staff) {
		List<Staff> userList = staffMapper.selectByCondition(staff);
		return userList;
	}

	@Override
	public StaffVo getStaffVo(Staff staff)  {
		return staffMapper.selectStaffVo(staff);
	}
	
	
}

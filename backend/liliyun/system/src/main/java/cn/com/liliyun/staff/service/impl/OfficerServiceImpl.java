package cn.com.liliyun.staff.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.common.util.PageUtil;
import cn.com.liliyun.staff.mapper.OfficerMapper;
import cn.com.liliyun.staff.model.Officer;
import cn.com.liliyun.staff.service.OfficerService;

@Service
public class OfficerServiceImpl implements OfficerService {

	@Autowired
	private OfficerMapper officerMapper;
	
	@Override
	public List<Officer> list(Officer officer) {
		PageUtil.startPage(officer);
		return officerMapper.selectByCondition(officer);
	}

	@Override
	public ResultBean add(Officer officer) {
		Officer off= officerMapper.selectByStaffid(officer);
		if(off!=null){
			ResultBean rb=new ResultBean("此员工已经是安全员");
			return rb;
		}
		officerMapper.insert(officer);
		return new ResultBean();
	}

	@Override
	public ResultBean del(Officer officer) {
		officerMapper.delete(officer);
		return new ResultBean();
	}

	@Override
	public ResultBean update(Officer officer) {
		officerMapper.update(officer);
		return new ResultBean();
	}

	@Override
	public Officer get(Officer officer) {
		Officer exist=officerMapper.selectByPrimaryKey(officer);
		return exist;
	}

}

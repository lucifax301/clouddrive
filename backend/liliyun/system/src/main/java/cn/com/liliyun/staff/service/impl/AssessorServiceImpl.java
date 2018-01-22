package cn.com.liliyun.staff.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.common.util.PageUtil;
import cn.com.liliyun.staff.mapper.AssessorMapper;
import cn.com.liliyun.staff.model.Assessor;
import cn.com.liliyun.staff.service.AssessorService;

@Service
public class AssessorServiceImpl implements AssessorService {

	@Autowired
	private AssessorMapper assessorMapper;
	
	@Override
	public List<Assessor> list(Assessor assessor) {
		PageUtil.startPage(assessor);
		return assessorMapper.selectByCondition(assessor);
	}

	@Override
	public ResultBean add(Assessor assessor) {
		Assessor off= assessorMapper.selectByStaffid(assessor);
		if(off!=null){
			ResultBean rb=new ResultBean("此员工已经是审核员");
			return rb;
		}
		assessorMapper.insert(assessor);
		return new ResultBean();
	}

	@Override
	public ResultBean del(Assessor assessor) {
		assessorMapper.delete(assessor);
		return new ResultBean();
	}

	@Override
	public ResultBean update(Assessor assessor) {
		assessorMapper.update(assessor);
		return new ResultBean();
	}

	@Override
	public Assessor get(Assessor assessor) {
		Assessor exist=assessorMapper.selectByPrimaryKey(assessor);
		return exist;
	}

}

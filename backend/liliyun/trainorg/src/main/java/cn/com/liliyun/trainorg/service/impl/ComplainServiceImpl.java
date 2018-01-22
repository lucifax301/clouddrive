package cn.com.liliyun.trainorg.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;

import cn.com.liliyun.common.util.PageUtil;
import cn.com.liliyun.trainorg.mapper.ComplainMapper;
import cn.com.liliyun.trainorg.mapper.ComplainReturnMapper;
import cn.com.liliyun.trainorg.model.Complain;
import cn.com.liliyun.trainorg.model.ComplainReturn;
import cn.com.liliyun.trainorg.service.ComplainService;

@Service
public class ComplainServiceImpl implements ComplainService {


	@Autowired
	private ComplainMapper complainMapper;

	@Autowired
	private ComplainReturnMapper complainReturnMapper;
	
	@Override
	public void addComplain(Complain complain) {

		complain.setStatus(new Integer(0));
		complainMapper.insertSelective(complain);
	}

	@Override
	public void updateComplain(Complain complain) {

		complainMapper.updateByPrimaryKeySelective(complain);
	}

	@Override
	public PageInfo<Complain> getComplainList(Complain complain) {

		PageUtil.startPage(complain);
		List<Complain> complainList = complainMapper.selectByCondition(complain);
		return new PageInfo<>(complainList);
	}

	@Override
	public void deleteById(Complain complain) {

		complainMapper.deleteByPrimaryKey(complain);
	}

	@Override
	public Complain getComplainById(Complain complain) {

		Complain result = complainMapper.selectByPrimaryKey(complain);
		return result;
	}

//	@Override
//	public int getCount(Complain complain) {
//		// TODO Auto-generated method stub
//		return 0;
//	}

	@Override
	public void importComplain(Complain complain, List<Complain> list) {
		// TODO Auto-generated method stub
		for (Complain c : list) {
			c.setDblink(complain.getDblink());
			c.setCtime(new Date());
			complainMapper.insert(c);
		}
	}

	@Override
	public PageInfo<ComplainReturn> getReturnList(ComplainReturn complainReturn) {
		// TODO Auto-generated method stub
		PageUtil.startPage(complainReturn);
		List<ComplainReturn> complainReturnList = complainReturnMapper.selectByCondition(complainReturn);
		return new PageInfo<>(complainReturnList);
	}


	@Override
	public void addComplainReturn(ComplainReturn complainReturn) {

		complainReturnMapper.insertSelective(complainReturn);
	}
	

}

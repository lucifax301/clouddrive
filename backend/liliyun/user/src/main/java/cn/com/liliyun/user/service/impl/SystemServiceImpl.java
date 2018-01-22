package cn.com.liliyun.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.liliyun.common.util.PageUtil;
import cn.com.liliyun.user.mapper.DicMapper;
import cn.com.liliyun.user.model.Dic;
import cn.com.liliyun.user.service.SystemService;

@Service
public class SystemServiceImpl implements SystemService {

	@Autowired
	private DicMapper dicMapper;
	
	@Override
	public void addDic(Dic dic) {
		dicMapper.insertSelective(dic);
	}

	@Override
	public void updateDic(Dic dic) {
		dicMapper.updateByPrimaryKeySelective(dic);
	}

	@Override
	public List<Dic> selectList(Dic dic) {
		PageUtil.startPage(dic);
		return dicMapper.selectList(dic);
	}

	@Override
	public void delDic(Dic dic) {
		dicMapper.deleteByPrimaryKey(dic);
	}

	@Override
	public List<Dic> selectValidList(Dic dic) {
		dic.setIsvalid(0);
		return dicMapper.selectList(dic);
	}
	
}

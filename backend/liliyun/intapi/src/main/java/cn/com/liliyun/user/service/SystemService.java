package cn.com.liliyun.user.service;

import java.util.List;

import cn.com.liliyun.user.model.Dic;

public interface SystemService {

	public void addDic(Dic dic);
	
	public void updateDic(Dic dic);
	
	public List<Dic> selectList(Dic dic);
	
	public List<Dic> selectValidList(Dic dic);
	
	public void delDic(Dic dic);
}

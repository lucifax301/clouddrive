package cn.com.liliyun.user.service;

import java.util.List;

import cn.com.liliyun.user.model.Dic;

public interface SystemService {

	void addDic(Dic dic);
	
	void updateDic(Dic dic);
	
	List<Dic> selectList(Dic dic);
	
	List<Dic> selectValidList(Dic dic);
	
	void delDic(Dic dic);
}

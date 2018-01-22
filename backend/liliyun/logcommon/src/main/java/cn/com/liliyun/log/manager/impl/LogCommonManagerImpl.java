package cn.com.liliyun.log.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.liliyun.log.mapper.LogCommonMapper;
import cn.com.liliyun.log.model.LogCommon;

@Service
public class LogCommonManagerImpl {

	@Autowired
	LogCommonMapper logCommonMapper;
	
	public List<LogCommon> findLogList(LogCommon dto) {
		return logCommonMapper.findBatch(dto);
	}

	
	public void insertLogList(List<LogCommon> logCommonList) {
		logCommonMapper.inertLogList(logCommonList);
	}

	
	public void insertLogInfo(LogCommon logCommon) {
		logCommonMapper.insert(logCommon);
		
	}
	

}

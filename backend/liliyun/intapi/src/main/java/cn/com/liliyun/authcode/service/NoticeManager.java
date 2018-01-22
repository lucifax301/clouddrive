package cn.com.liliyun.authcode.service;

import cn.com.liliyun.common.model.ResultBean;


public interface NoticeManager {
	public ResultBean getNoticesByUserId(String userId, String schoolId,  String pageNo, String pageSize);
	
	
}

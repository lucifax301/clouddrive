package cn.com.liliyun.common;

import cn.com.liliyun.common.model.RequestContext;

public class CommonService {

	public String getMethodName(){
		return Thread.currentThread().getStackTrace()[1].getMethodName();
	}
	
	public <T> T getContextValue(String key){
		return RequestContext.<T>getValue(key);
	}
}

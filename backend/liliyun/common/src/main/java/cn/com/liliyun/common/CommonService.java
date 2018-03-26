package cn.com.liliyun.common;

public class CommonService {

	public String getMethodName(){
		return Thread.currentThread().getStackTrace()[1].getMethodName();
	}
	
	
}

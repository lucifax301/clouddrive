package cn.com.liliyun.common.util;

import com.github.pagehelper.PageHelper;

import cn.com.liliyun.common.model.BaseModel;

public class PageUtil {
	
	/**
	 * 请求分页DTO入参继承该对象
	 * PageNo = -1时 不分页
	 * @param params
	 */
	public static void startPage(BaseModel model) {
		if (model.getPageNo() == -1) {
			return;
		}
		PageHelper.startPage(model.getPageNo(),model.getPageSize()); 
	}
}

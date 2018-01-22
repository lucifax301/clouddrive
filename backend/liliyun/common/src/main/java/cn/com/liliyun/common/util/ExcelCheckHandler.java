package cn.com.liliyun.common.util;

import java.util.List;
import java.util.Map;


public interface ExcelCheckHandler {

	/**
	 * 验证上传的数据
	 * @param list 列表数据
	 * @param title 标题栏
	 * @return
	 */
	public Map<String, Object> check(List<Object> list,String title);
	
}

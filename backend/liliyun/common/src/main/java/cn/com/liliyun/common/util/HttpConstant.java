package cn.com.liliyun.common.util;

public class HttpConstant {


	/**
	 * 请求正确码
	 */
	public static final int SUCCESS_CODE = 0;

	/**
	 * 该操作需要登录
	 */
	public static final int NEED_LOGIN_COCE = 1;
	
	/**
	 * 业务错误吗
	 */

	public static final int BIZ_ERROR_COCE = 2;
	
	/**
	 * 您没有该项操作的权利
	 */
	public static final int NO_AUTH_COCE = 120;

	/**
	 * 数据异常
	 */
	public static final int DATA_ERROR_COCE = 110;
	
	/**
	 * 请求错误码
	 */
	public static final int ERROR_CODE = 600;

	/**
	 * 请求正确信息
	 */
	public static final String SUCCESS_MSG = "操作成功!";
	
	/**
	 * 请求错误信息
	 */
	public static final String ERROR_MSG = "操作失败!";

	/**
	 * 数据异常
	 */
	public static final String DATA_ERROR_MSG = "数据异常";
}


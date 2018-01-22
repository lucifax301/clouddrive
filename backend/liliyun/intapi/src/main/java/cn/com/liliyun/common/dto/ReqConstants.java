package cn.com.liliyun.common.dto;


public class ReqConstants {
	/**
	 * 令牌名称
	 */
	public static final String TOKEN="token";
	
	/**
	 * 环境信息：测试环境
	 */
	public static final String ENV_TEST = "test";
	/**
	 * 环境信息：生产环境
	 */
	public static final String ENV_PRODUCT = "product";
	
	
	
	/**
	 * 用户状态：正常
	 */
	public static final int USER_STATE_NORMAL = 0;
	/**
	 * 用户状态：临时锁定
	 */
	public static final int USER_STATE_LOCKED_TEMP = 1;
	/**
	 * 用户状态：永久锁定
	 */
	public static final int USER_STATE_LOCKED_FOREVER = 2;
	
	/**
	 * 在职
	 */
	public static final int USER_EMPLOY_STATUS_IN = 0;
	
	/**
	 * 离职
	 */
	public static final int USER_EMPLOY_STATUS_OUT = 1;
	
	
	
	
	
	/**
	 * 用户性别：男
	 */
	public static final int USER_SEX_MAN = 1;
	/**
	 * 用户性别：女
	 */
	public static final int USER_SEX_WOMAN = 0;
	
	/**
	 * 用户名称：学员，默认名称
	 */
	public static final String USER_NAME_STUDENT_DEFAULT = "喱喱同学";
	
	/**
	 * 图片类型：用户头像
	 */
	public static final int PIC_TYPE_USER_HEADICON = 0;
	
	/**
	 * 请求类型：用户注册
	 */
	public static final int REQ_TYPE_REGISTER = 1;
	/**
	 * 请求类型：找回密码
	 */
	public static final int REQ_TYPE_FIND_PASSWORD = 2;
	/**
	 * 请求类型：短信登录
	 */
	public static final int REQ_TYPE_LOGIN = 3;
	/**
	 * 请求类型：超级登录短信。如果未注册则直接注册成用户，并返回登录短信；如果已注册则直接返回登录短信；
	 */
	public static final int REQ_TYPE_SUPER_LOGIN = 4;
	
	/**
	 * 请求类型：驾培云找回密码；
	 */
	public static final int REQ_TYPE_JPY_PWD= 5;
	
	
	/**
	 * 驾驶类型：C1 手动挡
	 */
	public static final int DRIVE_TYPE_C1 = 1;
	/**
	 * 驾驶类型：C2自动挡
	 */
	public static final int DRIVE_TYPE_C2 = 2;
	
	
}























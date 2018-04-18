package cn.com.liliyun.common.util;

public class ConstantUtil {
	
	public static final String ROUTE_DB = "DB_ROUTE";
	
	public static final String MRG = "MGR";
	//服务器主库连接
	public static final String LILIYUN_DB = "liliyun";
	//注册审核状态 0:未审核 1：审核通过 2：审核拒绝
	public static final int REGISTER_UNCHECK = 0;
	
	public static final int REGISTER_PASS = 1;
	
	public static final int REGISTER_REFUSE = 2;
	
	public static final byte USER_COMMON = 0;
	
	public static final byte USER_SUPER = 1;
	
	public static final String USER_SESSION = "liliyun_loginuser";
	
	public static final String SMS_CODE_REG_CODE = "131296";
	
	public static final String SMS_CODE_PWD_CODE = "loginuser";
	
	public static final int IS_AUTO_LOGIN = 1;
	
	public static final String SYNCH_DATA_TABLE_KEY = "table$name";
	
	public static final String SYNCH_DATA_TYPE_KEY = "synch$type";
	
	public static final String SYNCH_DATA_TYPE_INSERT = "INSERT";
	
	public static final String SYNCH_DATA_TYPE_UPDATE = "UPDATE";
	
	public static final String SYNCH_DATA_TYPE_DELETE = "DELETE";
	
	public static final String TOPIC_SYNCH = "synch_data";
	
	public static final String SESSION_BUSINESS="CURRENT_BUSINESS";
	
	public static final int AUDIT_ACCEPT=1;
	
	public static final int AUDIT_REJECT=2;
	
	public static final int AUDIT_CANCEL=3;
	
	//可以执行审核操作
	public static final int AUDIT_RIGHT_CAN_AUDIT=1;
	//可以执行取消操作
	public static final int AUDIT_RIGHT_CAN_CANCEL=2;
	
	public static final int AUDIT_RIGHT_CAN_MOD=3;
	//什么都不能做,只能看
	public static final int AUDIT_RIGHT_NONE=0;
	
	//职能员工
	public static final int STAFF_COMMON=1;
	//教练员
	public static final int STAFF_COACH=2;
	//片区主管
	public static final int STAFF_DIRECTOR=3;
	//客服
	public static final int STAFF_CS=5;
	//顶班客服
	public static final int STAFF_VICE_CS=6;
	
	public static final String STUDENT_NOTEXIST = "学员不存在";
}

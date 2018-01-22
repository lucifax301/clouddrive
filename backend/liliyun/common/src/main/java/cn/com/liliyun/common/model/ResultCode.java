package cn.com.liliyun.common.model;


import java.util.HashMap;
import java.util.Map;

public class ResultCode
{
	public static Map<Object,String> codeInfo=new HashMap<Object,String>();
	public static String getCodeInfo(Object code){
		  return codeInfo.get(code);
	}

    public class RESULTKEY {
        public static final String CODE="code";
        public static final String MSGKEY="msgKey";
        public static final String MSGINFO="msgInfo";
        public static final String DATA="data";
    }
    public class ERRORCODE {
        public static final int SUCCESS=0;
        public static final int NEEDLOGIN=1;
        public static final int NOAUTH=2;
        public static final int PARAMERROR=3;
        public static final int FAILED=4;
        public static final int EXCEPTION=5;
        public static final int DATAEXIST=6;
        public static final int COACHAPPLYEXIST=7;
        public static final int APPLYHASAUDIT=8;
        public static final int PAUSEAPPLYEXIST=9;
        
        public static final int USER_LOGIN_FAIL=10;
        public static final int USER_ACCOUNT_BAN=11;
        public static final int AUTHCODE_ERROR=12;
        public static final int USER_PASSWORD_ERROR=13;
        public static final int USER_NOT_EXIT=14;
		public static final int MOBILE_NUMBER_ERROR = 15;
		public static final int MOBILE_NOT_EXIT_ERROR = 16;
    }
    public class ERRORINFO {
        public static final String SUCCESS="操作成功";
        public static final String NEEDLOGIN="该操作需要登录";
        public static final String NOAUTH="您没有该项操作的权利";
        public static final String PARAMERROR="操作参数错误，请更正后重试";
        public static final String FAILED="操作失败，请重试";
        public static final String EXCEPTION="网络连接失败";
        public static final String DATAEXIST="数据已经存在";
        public static final String COACHAPPLYEXIST="该教练当前已有修改申请正在等待审核，不能再次提交修改申请哦。";
        public static final String APPLYHASAUDIT="数据已经被审核通过或者审核不通过";
        public static final String PAUSEAPPLYEXIST="该学员当前已有申请正在等待审核，不能再次提交申请哦。";
        
        public static final String USER_LOGIN_FAIL="帐号或密码错误，请重新输入";
        public static final String USER_ACCOUNT_BAN="帐号已停用";
        public static final String AUTHCODE_ERROR="验证码错误";
        public static final String USER_PASSWORD_ERROR="旧密码错误，请重新输入";
        public static final String USER_NOT_EXIT="用户不存在";
        public static final String MOBILE_NUMBER_ERROR="手机号码错误";
        public static final String MOBILE_NOT_EXIT_ERROR="手机号码不存在";
    }
    static {
    	codeInfo.put(ERRORCODE.SUCCESS, ERRORINFO.SUCCESS);
    	codeInfo.put(ERRORCODE.NEEDLOGIN, ERRORINFO.NEEDLOGIN);
    	codeInfo.put(ERRORCODE.NOAUTH, ERRORINFO.NOAUTH);
    	codeInfo.put(ERRORCODE.PARAMERROR, ERRORINFO.PARAMERROR);
    	codeInfo.put(ERRORCODE.FAILED, ERRORINFO.FAILED);
    	codeInfo.put(ERRORCODE.EXCEPTION, ERRORINFO.EXCEPTION);
    	
    	codeInfo.put(ERRORCODE.USER_LOGIN_FAIL, ERRORINFO.USER_LOGIN_FAIL);
    	codeInfo.put(ERRORCODE.AUTHCODE_ERROR, ERRORINFO.AUTHCODE_ERROR);
    	codeInfo.put(ERRORCODE.USER_NOT_EXIT, ERRORINFO.USER_NOT_EXIT);
    	codeInfo.put(ERRORCODE.MOBILE_NUMBER_ERROR, ERRORINFO.MOBILE_NUMBER_ERROR);
    	codeInfo.put(ERRORCODE.MOBILE_NOT_EXIT_ERROR, ERRORINFO.MOBILE_NOT_EXIT_ERROR);

    }
}

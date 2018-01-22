package cn.com.liliyun.httpaccess.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cn.com.liliyun.common.util.ConstantUtil;
import cn.com.liliyun.common.util.WebUtil;
import cn.com.liliyun.user.model.User;


public class AccessWebUtil extends WebUtil{


	public static final String BLANK_STR = "";
	public static final int ERROR_COCE = 401;
	public static final int SUCCESS_COCE = 200;
	
	public static final String ERROR_STR = "fail";
	public static final String SUCCESS_STR = "success";
	public static final String RESPONSE_STATUS = "success";
	//Cookies过期时间
	public static final int COOKIES_EXPIRY_SECONDS = 259200;
	public static final int COOKIES_LOGOUT_SECONDS = 0;
	public static final String COOKIE_ACCOUNT_STR = "lilixc";

	public static String currentUserLogInfo(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute(ConstantUtil.USER_SESSION);
		return user != null ? ("user account:'" + user.getRealname() + "',userName:'" + user.getRealname() + "'|")
				:"no user in session,request from ip: " + getRemortIP(request) + "|";
	}
	
	public static User getSessionUser(HttpServletRequest request){
    	HttpSession session = request.getSession();
		return (User) session.getAttribute(ConstantUtil.USER_SESSION);
	}
	
}

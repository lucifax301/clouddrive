package cn.com.liliyun.httpaccess.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.com.liliyun.common.model.RequestContext;
import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.common.util.ConstantUtil;
import cn.com.liliyun.common.util.GsonUtil;
import cn.com.liliyun.httpaccess.util.DevProperties;
import cn.com.liliyun.user.model.User;
import cn.com.liliyun.user.service.UserService;

/*
 * 权限拦截器
 */
public class PrivilageInterceptor extends HandlerInterceptorAdapter {

	protected final Logger access = Logger.getLogger(this.getClass());

	@Autowired
	private UserService userService;
	
	@Resource(name="devProperties")
	private DevProperties devProperties;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String url = request.getRequestURL().toString();
		ResultBean rb = new ResultBean();
		HttpSession session = request.getSession();
		
		if(devProperties.getDev().equals("0")){
//			User user0=new User();
//			user0.setId(22);
//			user0.setUsername("admin");
//			user0.setRealname("系统管理员");
//			user0.setLevel((byte)2);
//			user0.setAreaid(3);
//			user0.setStoreid(3);
//			user0.setStorenum("1");
//			user0.setAreanum("22");
			User user0=new User();
			user0.setId(22);
			user0.setUsername("admin");
			user0.setRealname("系统管理员");
			user0.setLevel((byte)2);
			user0.setRoleid(1);
			user0.setAreaid(3);
			user0.setStoreid(3);
			user0.setStorenum("1");
			user0.setAreanum("22");
			user0.setRoleid(1);
			user0.setStorename("宝安区虹桥门店");
			session.setAttribute(ConstantUtil.USER_SESSION, user0); 
        	return true;
		}
		
		if (session.getAttribute(ConstantUtil.USER_SESSION) == null) {
		    rb.setCode(-1);
		    rb.setMsg("用户请登录!");
			printJson(response, rb,null);
			return false;
		} 
		
		User user = (User) session.getAttribute(ConstantUtil.USER_SESSION);
		RequestContext rc = RequestContext.getOrCreate();
		RequestContext.putValue(ConstantUtil.USER_SESSION, user);
		
		return true;
//		access.debug("************************************Privilege check!");
//		if (user != null) {
//			User exist = userService.getUser(user);
//			if (exist == null) {
//				rb.setCode(100);
//				rb.setMsg("账号异常,请重新登录！");
//				printJson(response, rb);
//				return false;
//			}
//			if (exist.getStatus() == 1) {
//				access.debug("***********************************account disabled:" + exist.getUsername());
//				rb.setCode(100);
//				rb.setMsg("帐号停用！");
//				printJson(response, rb);
//				return false;
//			}
//			List<Privilege> privileges = user.getPrivileges();
//			if (privileges == null) {
//				rb.setCode(120);
//				rb.setMsg("没有权限!");
//				printJson(response, rb);
//				return false;
//			}
//			for (Privilege p : privileges) {
//				if (p.getAjax() != null && p.getAjax().length() > 1) {
//					String s[] = p.getAjax().split(",");
//					for (int i = 0; i < s.length; i++) {
//
//						if (s[i].length() > 0 && url.endsWith(s[i])) {
//							access.debug("***********************************" + user.getUsername()
//									+ " has privilege for " + url);
//							return true;
//						}
//					}
//				}
//			}
//			access.debug("***********************************" + user.getUsername() + " has not privilege for " + url);
//			rb.setCode(120);
//			rb.setMsg("没有权限!");
//			printJson(response, rb);
//			return false;
//		} else {
//			rb.setCode(120);
//			rb.setMsg("没有权限!");
//			printJson(response, rb);
//			return false;
//		}
	}
	
	@Override
	public void postHandle(    
            HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)    
            throws Exception {    
		RequestContext.set(null);
    }   
	
	@Override
	public void afterCompletion(    
            HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)    
            throws Exception { 
		if(ex!=null){
			ResultBean rb = new ResultBean(1,"发生异常了");
			printJson(response,rb,ex);
		}
    }  
	
	private void printJson(HttpServletResponse response,ResultBean rb,Exception ex) {
		response.setContentType("application/json"); 
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = null;

		if(ex!=null){
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			ex.printStackTrace(pw);
			rb.setStack(sw.toString());
		}
		try {
			out = response.getWriter();
			out.print(GsonUtil.serialNulls(rb));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		out.flush();
		out.close();
	}
}

package cn.com.liliyun.httpaccess.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.com.liliyun.business.model.ActionBusiness;
import cn.com.liliyun.business.service.BusinessService;
import cn.com.liliyun.common.util.ConstantUtil;
import cn.com.liliyun.user.model.User;

/**
 * 
 * @author devil
 * 业务拦截器
 */
public class BusinessInterceptor extends HandlerInterceptorAdapter{

	protected final Logger access = Logger.getLogger(this.getClass());
	
	@Autowired
	private BusinessService businessService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String url = request.getRequestURI();
		HttpSession session = request.getSession();
		String contexturl=session.getServletContext().getContextPath();
		url=url.replace(contexturl, "");
		while(url.startsWith("//")){
			url=url.replace("//", "/");
		}
		String actionid=url;
		
		User user = (User) session.getAttribute(ConstantUtil.USER_SESSION);
		if(user!=null){
		ActionBusiness ab=new ActionBusiness();
		System.out.println("==================="+user.getDblink());
		ab.setDblink(user.getDblink());
		ab.setActionid(actionid);
		ActionBusiness actionBusiness=businessService.getActionBusiness(ab);
		if(actionBusiness!=null){
			session.setAttribute(ConstantUtil.SESSION_BUSINESS, actionBusiness.getBusinessid());
		}
		}
		return true;
		
	}
}

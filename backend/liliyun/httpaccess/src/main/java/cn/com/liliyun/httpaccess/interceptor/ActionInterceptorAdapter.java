package cn.com.liliyun.httpaccess.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.com.liliyun.common.model.RequestContext;

/**
 * mvc最外层拦截器 ，创建和释放RequestContext
 * @author devil
 *
 */
public class ActionInterceptorAdapter extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("###### create RequestContext");
		RequestContext.create();
		return true;
	}
	
	@Override
	public void postHandle(    
            HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)    
            throws Exception {    
    }   
	
	@Override
	public void afterCompletion(    
            HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)    
            throws Exception { 
		System.out.println("###### clear RequestContext");
		RequestContext.set(null);
    } 
}

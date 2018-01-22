package cn.com.liliyun.httpaccess.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.liliyun.common.util.SessionHolder;


public class SessionFilter implements Filter{

	public void destroy() {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        SessionHolder.setRequest((HttpServletRequest)request);  
        SessionHolder.setResponse((HttpServletResponse)response);  
    }

    public void init(FilterConfig filterConfig) throws ServletException {

    }

}

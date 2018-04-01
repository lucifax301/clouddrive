package cn.com.liliyun.httpaccess.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import cn.com.liliyun.common.model.RequestContext;
import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.common.util.GsonUtil;

public class ActionFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		try{
			RequestContext rc = RequestContext.create();
			filterChain.doFilter(arg0, response);
		}catch(Throwable e){
			printJson(response,e);
		}finally{
			RequestContext.set(null);
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

	private void printJson(ServletResponse response,Throwable ex) {
		response.setContentType("application/json"); 
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = null;
		ResultBean rb = new ResultBean();
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

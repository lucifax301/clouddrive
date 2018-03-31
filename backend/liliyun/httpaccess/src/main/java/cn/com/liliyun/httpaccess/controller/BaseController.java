package cn.com.liliyun.httpaccess.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.common.util.ConstantUtil;
import cn.com.liliyun.common.util.GsonUtil;
import cn.com.liliyun.common.util.HttpConstant;
import cn.com.liliyun.user.model.User;

import com.github.pagehelper.PageInfo;

public class BaseController {

	protected static final String SECRET = "6b1019d9561c548037b37023d7a0451b"; 

	/**
	 * 统计集中处理exception
	 * @param request
	 * @param response
	 * @param ex
	 */
	@ExceptionHandler({RuntimeException.class,Exception.class})  
    public void exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception ex) {  
		//ex.printStackTrace();
		System.out.println("exceptionHandler");
        ResultBean rb = new ResultBean(HttpConstant.ERROR_CODE,HttpConstant.ERROR_MSG+":"+ex.getMessage());
    	printJson(response, rb,ex);
    }
	
	/**
	 * 获取当前用户
	 * @param request
	 * @return
	 */
	protected User getUser(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute(ConstantUtil.USER_SESSION);
		return user;
	}
	
	/**
	 * 获取dblink
	 * @param request
	 * @return
	 */
	protected String getDblink(HttpServletRequest request) {
		return ((User) request.getSession().getAttribute(ConstantUtil.USER_SESSION)).getDblink();
	}
	
	/**
	 * 获取excel文件上传路径
	 * @param request
	 * @return
	 */
	protected String getUploadPath(HttpServletRequest request) {
		return request.getSession().getServletContext().getRealPath("WEB-INF") + "/uploadExcel/";
	}
	
	/**
	 * 获取签名的算法，应该与客户端统一
	 * @param params 除sign之外的其他参数
	 * @param secret 密钥
	 * @return	签名字符串
	 */
	protected String getSignature(Map<String, String> params, String secret) {
		// 先将参数以其参数名的字典序升序进行排序
		Map<String,String> sortedParams = new TreeMap<String,String>(params);
		Set<Entry<String, String>> entrys = sortedParams.entrySet();
		// 遍历排序后的字典，将所有参数按"key=value"格式拼接在一起
	    StringBuilder basestring = new StringBuilder();
	    for (Entry<String, String> param : entrys) {
	        basestring.append(param.getKey()).append("=").append(param.getValue());
	    }
	    // 添加secret值到末尾
	    basestring.append(secret);
	    // 使用MD5对待签名串求签
	    byte[] bytes = null;
	    try {
	        MessageDigest md5 = MessageDigest.getInstance("MD5");
	        bytes = md5.digest(basestring.toString().getBytes("UTF-8"));
	    } catch (Exception ex) {
	    }
	    // 将MD5输出的二进制结果转换为小写的十六进制
	    StringBuilder sign = new StringBuilder();
	    for (int i = 0; i < bytes.length; i++) {
	        String hex = Integer.toHexString(bytes[i] & 0xFF);
	        if (hex.length() == 1) {
	            sign.append("0");
	        }
	        sign.append(hex);
	    }
	    return sign.toString();
	}
	
	protected Long getTimestamp() {
		return System.currentTimeMillis()/1000L;
	}
	
	protected void printJson(HttpServletResponse response,ResultBean rb,Exception ex) {
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
	
//	protected LogCommon initLogParams(HttpServletRequest request,int menuId,int action) {
//		final LogCommon logCommon = new LogCommon();
//		logCommon.setMenuid(menuId);
//		logCommon.setAction(action);
//		
//		logCommon.setOperatetime(DateUtil.now());
//		logCommon.setStatus(1);
//		logCommon.setUserid(AccessWebUtil.getSessionUser(request).getId()+"");
//		logCommon.setUsername(AccessWebUtil.getSessionUser(request).getUsername());
//		logCommon.setIp(NetworkUtil.getIpAddress(request));
//		
//		return logCommon;
//	}
	
	@InitBinder    
	public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");    
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));  
	}  
	
	public static void sendExcel(HttpServletResponse response, Workbook wb,String fileName) throws IOException {
		OutputStream os = null;
		try {
			os = response.getOutputStream();  
			response.setHeader("Content-disposition", "attachment;filename=" +fileName);  
			response.setContentType("application/vnd.ms-excel");  
			wb.write(os);  
			os.flush();  
			os.close();
		} catch (Exception e) {
			
		}  finally {
			try {
				if(os != null) {
					os.close();
				}
			} 
			catch (IOException e) {
				throw new IOException(e.getMessage());
			}
		}
	}
	
	public <T> ResultBean buildListResult(List<T> list){
		ResultBean rb = new ResultBean();
		rb.setResult(new PageInfo<T>(list));
		return rb;
	}
	
	public <T> ResultBean buildResult(T obj){
		ResultBean rb = new ResultBean();
		rb.setResult(obj);
		return rb;
	}
}
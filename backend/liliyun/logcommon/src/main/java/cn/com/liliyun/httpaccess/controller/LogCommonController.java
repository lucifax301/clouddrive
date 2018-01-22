package cn.com.liliyun.httpaccess.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.log.manager.impl.LogCommonManagerImpl;
import cn.com.liliyun.log.model.LogCommon;

import com.github.pagehelper.PageInfo;




/**
 * 日志统一操作
 * @author Administrator
 *
 */
@Controller
@Scope("prototype")
@RequestMapping("/logCommon")
public class LogCommonController{
	
	@Autowired
	LogCommonManagerImpl LogCommonService;
	
	/**
	 * 获取记录日志
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/batch", method = RequestMethod.GET)
	@ResponseBody
	public ResultBean batch(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try {
			String id=request.getParameter("id");
			LogCommon lc=new LogCommon();
			lc.setRelateid(id);
			lc.setRelatetable("coach");
			List<LogCommon> logs= LogCommonService.findLogList(lc);
			PageInfo<LogCommon> pagedResult=new PageInfo(logs);
			ResultBean resultBean = new ResultBean();
			resultBean.setResult(pagedResult);
			return resultBean;
		} catch (Exception e) {
			throw new Exception("异常:" + e.getMessage());
		}
		
	}

	
}

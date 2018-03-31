package cn.com.liliyun.httpaccess.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.liliyun.common.annotation.ActionDescription;
import cn.com.liliyun.common.model.RequestContext;
import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.common.util.ConstantUtil;
import cn.com.liliyun.httpaccess.controller.BaseController;
import cn.com.liliyun.trainorg.model.Trainarea;
import cn.com.liliyun.user.model.User;

@Controller
@ResponseBody
@RequestMapping(value="/test")
public class TestController extends BaseController {

	@ActionDescription(description="ok",error="failed")
	@RequestMapping(value="/edit")
	public ResultBean edit(Trainarea trainarea) {
		ResultBean rb = new ResultBean();
		User user = RequestContext.getValue(ConstantUtil.USER_SESSION);
		System.out.println(user);
		if(1==1)
		throw new RuntimeException("testerror");
		System.out.println("dotest----------------");
		return rb;
	}
}

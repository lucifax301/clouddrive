package cn.com.liliyun.httpaccess.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import cn.com.liliyun.common.model.BaseModel;
import cn.com.liliyun.common.util.ConstantUtil;
import cn.com.liliyun.user.model.User;

/**
 * controller层获取用户的session信息 ,并把session中数据库信息设置到BaseModel中。
 * @author lilixc
 */
@Aspect
@Component
public class DblinkInterceptor {

	//对spring的mapping注解做aop拦截
	@Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
	public void controllerAspect() {
	}

	@Before("controllerAspect()")
	public void injectDbInfo(JoinPoint joinPoint) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		Object [] args = joinPoint.getArgs();
		for (Object o : args) {
			if (o instanceof BaseModel) {
				User user = (User)session.getAttribute(ConstantUtil.USER_SESSION);
				if (user != null) {
					((BaseModel) o).setDblink(user.getDblink());
					((BaseModel) o).setSchoolname(user.getSchoolname());
					((BaseModel) o).setSchoolid(user.getSchoolid());
					((BaseModel) o).setDomain(user.getDomain());

				}
			}
		}
	}
}

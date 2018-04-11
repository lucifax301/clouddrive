package cn.com.liliyun.httpaccess.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import cn.com.liliyun.common.annotation.RequestAction;
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
		System.out.println("dblink已经记录下操作日志@Before 方法执行前");
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		Object [] args = joinPoint.getArgs();
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();  
        Method method = methodSignature.getMethod();
        //Class cls = method.getDeclaringClass();
        RequestAction requestAction = (RequestAction)method.getAnnotation(RequestAction.class);
		for (Object o : args) {
			if (o instanceof BaseModel) {
				User user = (User)session.getAttribute(ConstantUtil.USER_SESSION);
				
				if (user != null) {
					((BaseModel) o).setDblink(user.getDblink());
					((BaseModel) o).setSchoolname(user.getSchoolname());
					((BaseModel) o).setSchoolid(user.getSchoolid());
					((BaseModel) o).setDomain(user.getDomain());
					
					if(requestAction!=null){
						if(requestAction.type()==RequestAction.RequestActionType.ADD){
							((BaseModel) o).setCuid(user.getId());
						}else if(requestAction.type()==RequestAction.RequestActionType.UPDATE){
							((BaseModel) o).setMuid(user.getId());
						}
					}
				}
			}
		}
	}
	
	@Around("controllerAspect()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable{
		System.out.println("dblink已经记录下操作日志@Around 方法执行前");
        return pjp.proceed();
       
    }

    @After("controllerAspect()")
    public void after() {
        System.out.println("dblink已经记录下操作日志@After 方法执行后");
    }
}

package cn.com.liliyun.httpaccess.interceptor;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import cn.com.liliyun.common.annotation.ActionDescription;
import cn.com.liliyun.common.annotation.RequestAction;

/**
 * controller层获取用户的session信息 ,并把session中数据库信息设置到BaseModel中。
 * @author lilixc
 */
@Aspect
@Component
public class ControllerInterceptor {

	//对spring的mapping注解做aop拦截
	@Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
	public void controllerAspect() {
	}

	@Before("controllerAspect()")
	public void injectDbInfo(JoinPoint joinPoint) {
		System.out.println("controllerAspect 已经记录下操作日志@Before 方法执行前");
		
	}
	
	@Around("controllerAspect()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable{
		System.out.println("controllerAspect 已经记录下操作日志@Around 方法执行前");
		 MethodSignature methodSignature = (MethodSignature)pjp.getSignature();
		 Method method = methodSignature.getMethod();
		
		try{
			
         return pjp.proceed();
		}catch(Throwable e){
			System.out.println("############cat exception");
			ActionDescription description = (ActionDescription)method.getAnnotation(ActionDescription.class);
			String error = description!=null?description.error():"";
			e.printStackTrace();
			throw new RuntimeException(error+":"+e.toString(),e);
			//throw e;
		}
        
    }

    @After("controllerAspect()")
    public void after() {
        System.out.println("controllerAspect 已经记录下操作日志@After 方法执行后");
    }
}

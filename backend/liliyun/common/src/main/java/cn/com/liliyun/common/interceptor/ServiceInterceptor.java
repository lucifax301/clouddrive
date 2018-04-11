package cn.com.liliyun.common.interceptor;

import java.lang.reflect.Method;
import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import cn.com.liliyun.common.trace.InnerTrace;
import cn.com.liliyun.common.trace.Trace;

/**
 * 
 * @author lilixc
 * 不能嵌套拦截
 */
@Aspect
@Component
public class ServiceInterceptor {

	//拦截所有Servie注解的类的方法
	@Pointcut("@within(org.springframework.stereotype.Service)")
	public void serviceAspect() {
	}

	@Before("serviceAspect()")
	public void beforeMethod(JoinPoint joinPoint) {
		MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
		Method method = methodSignature.getMethod();
		System.out.println("beforeMethod "+method.getName()+" at time:"+new Date());
		InnerTrace innerTrace =Trace.createTrace();
		innerTrace.setMethodName(method.getName());
		System.out.println(innerTrace);
	}
	
//	@Around("serviceAspect()")
//    public void around(ProceedingJoinPoint pjp) throws Throwable{
//		System.out.println("已经记录下操作日志@Around 方法执行前");
//		 MethodSignature methodSignature = (MethodSignature)pjp.getSignature();
//		 Method method = methodSignature.getMethod();
//		
//		try{
//			
//        pjp.proceed();
//		}catch(Throwable e){
//			System.out.println("############cat exception");
//			ActionDescription description = (ActionDescription)method.getAnnotation(ActionDescription.class);
//			throw new RuntimeException(description.error()+":"+e.toString(),e);
//			//throw e;
//		}
//        
//    }

    @After("serviceAspect()")
    public void afterMethod(JoinPoint joinPoint) {
    	MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
		Method method = methodSignature.getMethod();
		System.out.println("endMethod "+method.getName()+" at time:"+new Date());
		InnerTrace innerTrace = Trace.endTrace();
		System.out.println(innerTrace);
    }
}

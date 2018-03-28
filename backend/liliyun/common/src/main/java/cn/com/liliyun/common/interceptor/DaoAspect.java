package cn.com.liliyun.common.interceptor;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import cn.com.liliyun.common.annotation.DBRoute;

public class DaoAspect {

	 
	 Object before(JoinPoint joinPoint) {
		 Object[] args = joinPoint.getArgs();
		 ProceedingJoinPoint pjp = (ProceedingJoinPoint) joinPoint;
         Object retVal = null;
         MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();  
         
         Method method = methodSignature.getMethod();  
         Class cls = method.getDeclaringClass();
         Object target = joinPoint.getTarget();  
         Type[] types = target.getClass().getGenericInterfaces();
         for(Type type:types){
        	 if (type instanceof ParameterizedType) {
	        	 System.out.println("1111");
	         }
	         //DBRoute route = type.getClass().getAnnotation(DBRoute.class);
         }
         
         DBRoute route = (DBRoute)cls.getAnnotation(DBRoute.class);
         try {
             retVal = pjp.proceed(args);
         } catch (Throwable throwable) {
             throwable.printStackTrace();
         }
         return retVal;
	 }
}

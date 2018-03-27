package cn.com.liliyun.common.interceptor;

import java.lang.annotation.Annotation;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class DaoInterceptor implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {

		Annotation[] annotations = invocation.getMethod() .getClass().getAnnotations();
		return invocation.proceed();
	}

}

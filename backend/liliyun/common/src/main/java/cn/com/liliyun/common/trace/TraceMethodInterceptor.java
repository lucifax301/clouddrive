package cn.com.liliyun.common.trace;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

public class TraceMethodInterceptor implements MethodInterceptor {

	@Override
	public Object intercept(Object o, Method method, Object[] objects,
			MethodProxy methodProxy) throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("beforeMethod"+method.getName());
		Object obj = methodProxy.invokeSuper(o, objects);
		System.out.println("endMethod"+method.getName());
		return obj;
	}

}

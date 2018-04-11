package cn.com.liliyun.common.trace;

import java.lang.reflect.Method;
import java.util.Date;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

public class TraceMethodInterceptor implements MethodInterceptor {

	@Override
	public Object intercept(Object o, Method method, Object[] objects,
			MethodProxy methodProxy) throws Throwable {
		// TODO Auto-generated method stub
		
		try{
			System.out.println("beforeMethod "+method.getName()+" at time:"+new Date());
			InnerTrace innerTrace =Trace.createTrace();
			innerTrace.setMethodName(method.getName());
			Object obj = methodProxy.invokeSuper(o, objects);
		
			return obj;
		}finally{
			System.out.println("endMethod "+method.getName()+" at time:"+new Date());
			InnerTrace innerTrace = Trace.endTrace();
		}
	}

}

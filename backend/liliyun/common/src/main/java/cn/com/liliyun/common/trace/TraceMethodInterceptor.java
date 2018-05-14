package cn.com.liliyun.common.trace;

import java.lang.reflect.Method;
import java.util.Date;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

public class TraceMethodInterceptor implements MethodInterceptor {
	
	Object targetObject = null;
	
	public TraceMethodInterceptor(Object obj){
		targetObject = obj;
	}

	@Override
	public Object intercept(Object o, Method method, Object[] objects,
			MethodProxy methodProxy) throws Throwable {
		// TODO Auto-generated method stub
		if(method.getName().equals("toString")||method.getName().equals("hashCode")){
			return methodProxy.invokeSuper(o, objects);
		}
		System.out.println("method:"+method.getName());
		try{
			System.out.println("TraceMethodInterceptor beforeMethod "+method.getName()+" at time:"+new Date());
			InnerTrace innerTrace =Trace.createTrace();
			innerTrace.setMethodName(method.getName());
			//o是TestServiceImpl$$EnhancerByCGLIB$$2e40b151, 调用invokeSuper 实际是调用TestServiceImpl$$EnhancerByCGLIB$$FastClassByCGLIB$$111d8bcd$$2e40b151的invoke方法
			//会对传入的o参数做TestServiceImpl$$EnhancerByCGLIB$$2e40b151强转型,如果这里传的不是TestServiceImpl$$EnhancerByCGLIB$$2e40b151,而是 TestServiceImpl就会ClassCastException
			//调用TestServiceImpl$$EnhancerByCGLIB$$2e40b151的fastclass的invoke方法
			Object obj = methodProxy.invokeSuper(o, objects);
			
			//这里会报ClassCastException
			//Object obj = methodProxy.invokeSuper(targetObject, objects);
			
			//调用TestServiceImpl的fastclass的invoke方法
			//Object obj = methodProxy.invoke(targetObject, objects);
			
			//StackOverflowError
			/**
			 *  at org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:204)
				at cn.com.liliyun.common.trace.TraceMethodInterceptor.intercept(TraceMethodInterceptor.java:41)
				at cn.com.liliyun.httpaccess.util.TestServiceImpl$$EnhancerByCGLIB$$91905376.dotest3(<generated>)
			 */
			//Object obj = methodProxy.invoke(o, objects);
			return obj;
		}finally{
			System.out.println("TraceMethodInterceptor endMethod "+method.getName()+" at time:"+new Date());
			InnerTrace innerTrace = Trace.endTrace();
		}
	}

}

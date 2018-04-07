package cn.com.liliyun.common.util;

import org.springframework.cglib.proxy.Enhancer;

import cn.com.liliyun.common.trace.TraceMethodInterceptor;

public class TraceCGLibUtil {

	public static <T> T createBean(Class<T> cls){
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(cls);
		enhancer.setCallback(new TraceMethodInterceptor());
		return (T)enhancer.create();
	}
}

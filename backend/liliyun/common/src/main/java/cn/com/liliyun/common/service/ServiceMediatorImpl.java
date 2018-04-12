package cn.com.liliyun.common.service;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import cn.com.liliyun.common.util.ApplicationContextUtil;

@Service
public class ServiceMediatorImpl implements ServiceMediator {

	@Override
	public <T> T getService(Class<T> cls) {
		return this.getBean(cls);
	}

	private <T> T getBean(Class<T> cls){
		T bean =(T) services.get(cls);
		if(bean==null){
			bean = ApplicationContextUtil.getBean(cls);
			services.putIfAbsent(cls, bean);
		}
		return bean;
	}
	
	private ConcurrentHashMap<Class<?>,Object> services = new ConcurrentHashMap<Class<?>,Object>(128);
}

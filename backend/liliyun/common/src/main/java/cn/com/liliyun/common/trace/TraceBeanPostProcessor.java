package cn.com.liliyun.common.trace;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Service;

import cn.com.liliyun.common.util.TraceCGLibUtil;

/**
 * 每个bean 实例化完属性注入完后修改bean,用cglib改写bean,加入拦截方法
 * @author Administrator
 *
 */
public class TraceBeanPostProcessor implements BeanPostProcessor {

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {
		
		//System.out.println("#################post bean:"+bean);
		Service service = (Service) bean.getClass().getAnnotation(Service.class);
		if(service!=null){
			Class<?> cls = bean.getClass();
			//System.out.println("post @@@@@@@bean"+cls);
			Object newbean = TraceCGLibUtil.createBean(cls);
			return newbean;
			
		}
		return bean;
	}

}

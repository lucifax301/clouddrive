package cn.com.liliyun.common.trace;

import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Service;

import cn.com.liliyun.common.annotation.ActionTrace;
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
		
		
		
		boolean isProxy = AopUtils.isAopProxy(bean);
		if(isProxy){
			Class<?> proxyedCls = bean.getClass().getSuperclass();
			System.out.println("#################post bean:"+ proxyedCls);
			ActionTrace service = (ActionTrace) proxyedCls.getAnnotation(ActionTrace.class);
			if(service!=null){
				
				System.out.println("post @@@@@@@bean"+proxyedCls);
				/**
				 * 这里传进来的是spring 代理过的bean, 如****ServiceCGlibBySpring$$**, 如果这时候在此bean基础上再封装一层一个新bean,就会是****ServiceCGLib$$***
				 * 导致在循环依赖的情况下，AbstractAutowireCapableBeanFactory 里doCreateBean方法判断bean和exposedObject 不是一个实体，导致异常
				 * 正常循环依赖，而没有再次创建****ServiceCGLib$$*** 的情况下 bean和exposedObject都是原始被代理的实体
				 * 但这里封装后exposedObject 是****ServiceCGLib$$***
				 */
				Object newbean = TraceCGLibUtil.createBean(proxyedCls);
				return newbean;
				
			}
			return bean;
		}else{
			System.out.println("#################post bean:"+bean);
			ActionTrace service = (ActionTrace) bean.getClass().getAnnotation(ActionTrace.class);
			if(service!=null){
				Class<?> cls = bean.getClass();
				System.out.println("post @@@@@@@bean"+cls);
				/**
				 * 这里传进来的是spring 代理过的bean, 如****ServiceCGlibBySpring$$**, 如果这时候在此bean基础上再封装一层一个新bean,就会是****ServiceCGLib$$***
				 * 导致在循环依赖的情况下，AbstractAutowireCapableBeanFactory 里doCreateBean方法判断bean和exposedObject 不是一个实体，导致异常
				 * 正常循环依赖，而没有再次创建****ServiceCGLib$$*** 的情况下 bean和exposedObject都是原始被代理的实体
				 * 但这里封装后exposedObject 是****ServiceCGLib$$***
				 */
				Object newbean = TraceCGLibUtil.createBean(cls);
				return newbean;
				
			}
		}
		return bean;
	}

}

package cn.com.liliyun.common.service;

/**
 * 服务调停者, 让所有服务解耦 不相互循环依赖
 * @author devil
 *
 */
public interface ServiceMediator {

	<T> T getService(Class<T> cls);
}

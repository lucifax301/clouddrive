package cn.com.liliyun.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextUtil implements ApplicationContextAware{

	private static ApplicationContext context;  
	  
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {  
        context = applicationContext;  
    }  
      
    public static ApplicationContext getApplicationContext() {  
        return context;  
    }  
    
    public static <T> T getBean(Class<T> cls){
    	return context.getBean(cls);
    }
}

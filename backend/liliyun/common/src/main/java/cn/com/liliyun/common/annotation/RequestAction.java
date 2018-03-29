package cn.com.liliyun.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RequestAction {

	RequestActionType type() ;
	
	enum RequestActionType{
		ADD,UPDATE,DELETE
	}
}

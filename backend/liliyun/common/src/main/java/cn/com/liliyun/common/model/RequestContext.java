package cn.com.liliyun.common.model;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RequestContext {

	private Map<String,Object> data = new ConcurrentHashMap<String,Object>(2);
	
	private static ThreadLocal<RequestContext> current = new ThreadLocal<RequestContext>();
	
	private RequestContext(){
		
	}
	
	public static RequestContext create(){
		return create(true);
	}
	
	public static RequestContext create(boolean setCurrent){
		RequestContext rc = new RequestContext();
		if(setCurrent)
			current.set(rc);
		return rc;
	}
	
	public static RequestContext get(){
		return current.get();
	}
	
	public static RequestContext getOrCreate(){
		RequestContext rc = current.get();
		if(rc == null){
			rc = create();
		}
		return rc;
	}
	
	public static void putValue(String key,Object value){
		RequestContext rc = getOrCreate();
		rc.data.put(key, value);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T  getValue(String key){
		RequestContext rc = getOrCreate();
		return (T)rc.data.get(key);
	}
	
	public static void set(RequestContext rc){
		current.set(rc);
	}
}

package cn.com.liliyun.common.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 
 * @author devil
 *
 */
public class LiliObjectFactory {

	private static ConcurrentHashMap<Class,Object> pool=new ConcurrentHashMap();
	
	public static Object getObject(Class clz){
		try{
			if(!pool.containsKey(clz)){
				pool.putIfAbsent(clz, clz.newInstance());
			}
			return pool.get(clz);
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
}

package cn.com.liliyun.log.util;

import java.lang.reflect.Field;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @author amos
 * 获取对象属性变化json,如{name:{from:a,to:b},value:{from:1,to:2}}
 *
 */
public class LogCommonUtil {

	public static JSONObject getChange(Object oldo,Object newo) throws Exception{
		JSONObject json=new JSONObject();
		Class oclz=(Class)oldo.getClass();
		Class nclz=(Class)newo.getClass();
		
		Field[] fs=oclz.getDeclaredFields();
		for(int i=0;i<fs.length;i++){
			Field f=fs[i];
			f.setAccessible(true);
			Object oval=f.get(oldo);
			Object nval=f.get(newo);
			
			JSONObject j=new JSONObject();
			if(!oval.equals(nval)){
			j.put("from", (oval==null?"null":oval));
			j.put("to", (nval==null?"null":nval));
			json.put(f.getName(), j);
			}
		}
		return json;
	}
	
	public static String getChangeString(Object oldo,Object newo) throws Exception{
		JSONObject json=new JSONObject();
		Class oclz=(Class)oldo.getClass();
		Class nclz=(Class)newo.getClass();
		
		Field[] fs=oclz.getDeclaredFields();
		for(int i=0;i<fs.length;i++){
			Field f=fs[i];
			f.setAccessible(true);
			Object oval=f.get(oldo);
			Object nval=f.get(newo);
			
			JSONObject j=new JSONObject();
			oval=oval==null?"null":oval;
			nval=nval==null?"null":nval;
			if(!oval.equals(nval)){
			j.put("from", (oval==null?"null":oval));
			j.put("to", (nval==null?"null":nval));
			json.put(f.getName(), j);
			}
		}
		return json.toJSONString();
	}
}

package cn.com.liliyun.common.util;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.beanutils.PropertyUtils;

public class BeanConvertUtil {
	
	/**
	 * 
	 * @param source
	 * @param target
	 * @param keys <targetAttr,sourceAttr>
	 */
	public static void convert(Object source,Object target,Map<String,String> keys) {
		Iterator<Entry<String, String>> keysItr = keys.entrySet().iterator();
		while (keysItr.hasNext()) {
			Entry<String, String> entry = keysItr.next();
			String targetAttr = entry.getKey();
			String sourceAttr = entry.getValue();
			try {
				PropertyUtils.setProperty(target, targetAttr , 
						PropertyUtils.getProperty(source, sourceAttr));
			} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
				e.printStackTrace();
			}
		}
	}
}

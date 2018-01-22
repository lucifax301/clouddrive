package cn.com.liliyun.common.util;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class MQSQLUtil {

	public static String getSQL(Map<String, Object> params) {
		String tableName = params.get(ConstantUtil.SYNCH_DATA_TABLE_KEY).toString();
		String synchType = params.get(ConstantUtil.SYNCH_DATA_TYPE_KEY).toString();
		String sql = synchType + " into " + tableName + " ";
		String fileds = "(";
		String values = "(";
		Iterator<Entry<String, Object>> itr = params.entrySet().iterator();
		while (itr.hasNext()) {
			Entry<String, Object> temp = itr.next();
			if (itr.hasNext()) {
				fileds += temp.getKey() + ",";
				values += temp.getValue() + ",";
			} else {
				fileds += temp.getKey();
				values += temp.getValue();
			}
		}
		fileds += ")";
		values += ")";
		return sql + fileds + "values" +  values;
	}
}

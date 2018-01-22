package common;

import java.util.HashMap;
import java.util.Map;

import cn.com.liliyun.common.util.BeanConvertUtil;

public class Test {

	public static void main(String[] args) {
		A a = new A();
		a.setName("joseph");
		a.setAge("333");
		B b = new B();
		Map <String,String> keys = new HashMap<>();
		keys.put("username","name");
		keys.put("myage","age");
		BeanConvertUtil.convert(a, b, keys);
		System.out.println(b);
	}
}
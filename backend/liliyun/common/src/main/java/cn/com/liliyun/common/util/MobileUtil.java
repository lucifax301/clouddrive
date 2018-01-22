package cn.com.liliyun.common.util;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MobileUtil {
	/** 
     * 手机号验证 
     *  
     * @param  str 
     * @return 验证通过返回true 
     */  
    public static boolean isMobile(String str) {   
        Pattern p = null;  
        Matcher m = null;  
        boolean b = false;   
        p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$"); // 验证手机号  
        m = p.matcher(str);  
        b = m.matches();   
        return b;  
    } 
    
	/**
	 * 生成六位随机数作为短信验证码
	 * @return
	 */
	public static Integer generateAuthCode(){
		
		int[] array = {0,1,2,3,4,5,6,7,8,9};

		Random rand = new Random();

		for (int i = 10; i > 1; i--) {
			int index = rand.nextInt(i);
			int tmp = array[index];
			array[index] = array[i - 1];
			array[i - 1] = tmp;
		}

		int result = 0;
		for(int i = 0; i < 6; i++)
			result = result * 10 + array[i];

		System.out.println(result);
		return result;
	}

}

package cn.com.liliyun.common.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.cloopen.rest.sdk.utils.encoder.BASE64Decoder;
import com.cloopen.rest.sdk.utils.encoder.BASE64Encoder;

public class EncryptUtil {
	
	public static void main(String[] args) {
		int a =(int) ((Math.random()*9+1)*100000);
		String account = "123456";
		String psw = DSHA1(account,a+"");
		System.out.println(account);
		System.out.println(psw.substring(psw.length()-6,psw.length()));
		System.out.println(psw);
	}
 

	/**
	 * 通过SHA1的两次加密
	 * @param str 明文密码
	 * @return
	 */
	public static String DSHA1(String str){
		int ramdon = (int) ((Math.random()*9+1)*100000);
		return DSHA1(str,ramdon+"");
	}
	
	/**
	 * 指定六位随机数,通过SHA1的两次加密
	 * @param str 明文密码
	 * @param ramdon 六位随机数
	 * @return
	 */
	public static String DSHA1(String str,String ramdon){
		return SHA1(SHA1(str + ramdon) + ramdon + ramdon) + "$" + ramdon;
	}

	/**
	 * 由pwd0原始密码双重加密
	 * @param pwd0 原始密码
	 * @param pwd2 数据库存储的双重加密的密码
	 * @return
	 */
	public static  String buildEncryptedPassword(String pwd0,String pwd2){
		return DSHA1(pwd0, getRamdon(pwd2));
	}
	

	/**
	 * 通过数据库密码获取随机数
	 * @param enPwd 数据库存储的双重加密的密码
	 * @return
	 */
	public static String getRamdon(String enPwd){
		return enPwd.substring(enPwd.length()-6, enPwd.length());
	}
	
    public static String SHA1(String decript) {
        try {
            MessageDigest digest = java.security.MessageDigest
                    .getInstance("SHA-1");
            digest.update(decript.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();
 
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
 
    public static String SHA(String decript) {
        try {
            MessageDigest digest = java.security.MessageDigest
                    .getInstance("SHA");
            digest.update(decript.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();
 
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
 
    public static String MD5(String input) {
    	input = input == null ? "" : input; 
        try {
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(input.getBytes());
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < md.length; i++) {
                String shaHex = Integer.toHexString(md[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
 
    /**
     * 加密
     *
     * @param content
     *            需要加密的内容
     * @param password
     *            加密密码
     * @return
     */
    public static byte[] encryptAES(String content, String password) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128, new SecureRandom(password.getBytes()));
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            byte[] byteContent = content.getBytes("utf-8");
            cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
            byte[] result = cipher.doFinal(byteContent);
            return result; // 加密
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }
 
    /**
     * 解密
     *
     * @param content
     *            待解密内容
     * @param password
     *            解密密钥
     * @return
     */
    public static byte[] decryptAES(byte[] content, String password) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128, new SecureRandom(password.getBytes()));
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
            byte[] result = cipher.doFinal(content);
            return result; // 加密
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }
 
    /**
     * BASE64解密
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static String decryptBASE64(String key) {
	  byte[] b = null;  
      String result = null;  
      if (key != null) {  
          BASE64Decoder decoder = new BASE64Decoder();  
          try {  
              b = decoder.decodeBuffer(key);  
              result = new String(b, "utf-8");  
          } catch (Exception e) {  
              e.printStackTrace();  
          }  
      }  
      return result;  
    }
 
    /**
     * BASE64加密
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static String encryptBASE64(String key) {
    	byte[] b = null;  
        String s = null;  
        try {  
            b = key.getBytes("utf-8");  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        }  
        if (b != null) {  
            s = new BASE64Encoder().encode(b);  
        }  
        return s;  
    }
}
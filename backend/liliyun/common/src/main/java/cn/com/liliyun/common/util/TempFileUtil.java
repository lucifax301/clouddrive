package cn.com.liliyun.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.util.SafeEncoder;
import cn.com.liliyun.common.model.DownloadFile;
import cn.com.liliyun.common.util.redis.RedisUtil;

import com.google.common.io.ByteSource;
import com.google.common.io.ByteStreams;

public class TempFileUtil {

	@Autowired
	RedisUtil redisUtil;
	
	public String save(String fileName,byte[] bytes,int timeout){
		String id = createId();
		byte[] key = SafeEncoder.encode(id);
		boolean result =false;
		while(!result){
			result = redisUtil.setNX(key, bytes, timeout);
			if(!result)
				continue;
			String fileNameKey=id+".tempfile";
			redisUtil.set(fileNameKey, fileName,timeout);
			break;
		}
		
		return generateUrl(id);
	}
	
	private String generateUrl(String id){
		return MessageFormat.format("tempfile/download.do?id={0}", id);
	}
	
	public String save(String fileName,InputStream in,int timeout){
		try{
			return save(fileName,ByteStreams.toByteArray(in),timeout);
		}catch(IOException e){
			throw new RuntimeException(e);
		}
	}
	
	public DownloadFile get(String id){
		byte[] key = SafeEncoder.encode(id);
		byte[] value = redisUtil.get(key);
		if(value==null){
			return null;
		}
		try{
			String fileNameKey=id+".tempfile";
			String fileName = redisUtil.get(fileNameKey);
			return new DownloadFile(value.length,ByteSource.wrap(value).openBufferedStream(),fileName);
		}catch(IOException e){
			throw new RuntimeException(e);
		}
	}
	
	static SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
	
	public static String createId(){
		return format.format(new Date())+"-" + UUID.randomUUID().toString();
	}
}

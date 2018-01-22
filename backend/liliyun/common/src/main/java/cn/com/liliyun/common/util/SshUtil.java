package cn.com.liliyun.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Properties;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

/**
 * 调用ssh运行脚本工具类
 * @author lilixc
 *
 */
public class SshUtil {
		
	/**
	 * 
	 * @param script ssh.properties中对应脚本路径的key值
	 * @param param 参数  多个参数时以空格隔开 没有参数填null
	 * @throws Exception
	 */
	public static void runExec(String script,String param) throws Exception {
		InputStream inputStream =  SshUtil.class.getClassLoader()
				.getResourceAsStream("ssh.properties");   
		final Properties props = new Properties();   
		try {   
			props.load(inputStream);   
		} catch (IOException e1) {   
		   e1.printStackTrace();   
		}   

		String scriptPath = props.getProperty(script);
		String host = props.getProperty("ssh.host");
		String user = props.getProperty("ssh.user");
		String password = props.getProperty("ssh.password");
		int port = Integer.parseInt(props.getProperty("ssh.port"));
		
	    JSch jsch = new JSch();
	    Session session = jsch.getSession(user, host , port);
	    session.setPassword(password);
	    session.setConfig("StrictHostKeyChecking","no");
	    session.connect();
	    ChannelExec channelExec = (ChannelExec)session.openChannel("exec");  
        channelExec.setCommand(String.format("/bin/sh %s %s", scriptPath, param == null ? "" : param));  
        channelExec.setInputStream(null);  
        channelExec.setErrStream(System.err);  
        InputStream in = channelExec.getInputStream();  
        channelExec.connect(); 
        BufferedReader reader = new BufferedReader(new InputStreamReader(in,Charset.forName("UTF-8")));
		String buf = null;
		while (reader.readLine() != null) {
			System.out.println(buf);
		} 
		channelExec.disconnect();
		session.disconnect();
		if (channelExec.getExitStatus() != 0) {
			throw new Exception();
		}
	}
}

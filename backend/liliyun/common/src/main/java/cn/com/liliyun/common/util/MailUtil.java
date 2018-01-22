package cn.com.liliyun.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

public class MailUtil {

	public static void main(String[] args) throws Exception {
       sendMail("119857812@qq.com","喱喱驾培云云数据库余量",String.format("重置密码验证码 (%s)","123456"));
    }
	
	public static void sendMail(String toUser, String subject, String content) throws Exception {
		InputStream inputStream =  MailUtil.class.getClassLoader().getResourceAsStream("email.properties");   
		final Properties props = new Properties();   
		try {   
			props.load(inputStream);   
		} catch (IOException e1) {   
		   e1.printStackTrace();   
		}   

        // 构建授权信息，用于进行SMTP进行身份验证
        Authenticator authenticator = new Authenticator() {
        	@Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // 用户名、密码
                String userName = props.getProperty("mail.user");
                String password = props.getProperty("mail.password");
                return new PasswordAuthentication(userName, password);
            }
        };
        // 使用环境属性和授权信息，创建邮件会话
        Session mailSession = Session.getInstance(props, authenticator);
        // 创建邮件消息
        MimeMessage message = new MimeMessage(mailSession);
        // 设置发件人
        InternetAddress form = new InternetAddress(props.getProperty("mail.user"));
        message.setFrom(form);
        // 设置收件人
        InternetAddress to = new InternetAddress(toUser);
        message.setRecipient(RecipientType.TO, to);
        // 设置邮件标题
        message.setSubject(subject);
        // 设置邮件的内容体
        message.setContent(content, "text/html;charset=UTF-8");
        // 发送邮件
        Transport.send(message);
	}
}


package cn.amy.shop.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailUitils {
	/*
	 * to收件人
	 * code激活码
	 */
	public static void sendMail(String to , String code){
		//1.获取session对象
		//2.创建一个代表邮件的对象Message
		//3.发送邮件Transport
		Properties props = new Properties();
		props.setProperty("mail.host", "localhost");
		Session session = Session.getInstance(props, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication("service@onlineshop.com", "123");
			}
			
		});
		
		Message message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress("service@onlineshop.com"));
			message.addRecipient(RecipientType.TO, new InternetAddress(to));
			message.setSubject("来自购物天堂的官方邮件");
			message.setContent("<h1>购物天堂的官方激活邮件<br>请点击下面的链接激活邮件</h1><h3><a href='http://localhost:8080/onlineShop/user_active.action?code="+code+"'>http://localhost:8080/onlineShop/user_active.action?code="+code+"</a></h3>", "text/html;charset=UTF-8");
			Transport.send(message);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

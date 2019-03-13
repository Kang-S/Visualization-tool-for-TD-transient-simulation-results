import java.util.Properties;
import java.io.FileNotFoundException;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import javax.mail.internet.MimeMultipart; 
import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeBodyPart; 
import javax.activation.DataHandler; 
import javax.activation.FileDataSource;  
/**
 * 实现邮件发送功能
 * @author huang
 *
 */
public class MailTest {
	   public void send() throws Exception {
		   final Properties props = new Properties();
	        /*
	         * 可用的属性： mail.store.protocol / mail.transport.protocol / mail.host /
	         * mail.user / mail.from
	         */
	        // 表示SMTP发送邮件，需要进行身份验证
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.host", "smtp.sina.com");
	        // 发件人的账号
	        props.put("mail.user", "javatest0930@sina.com");
	        // 访问SMTP服务时需要提供的密码
	        props.put("mail.password", "javatest");

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
	        InternetAddress to = new InternetAddress("244992140@qq.com");
	        message.setRecipient(RecipientType.TO, to);
	        
	        // 设置邮件标题
	        message.setSubject("javatest");

	        // 设置邮件的内容体
//	        设置邮件图片1  
	        MimeBodyPart text = new MimeBodyPart(); 
	        text.setContent("此邮件为系统自动发送<img src='cid:a'>", "text/html;charset=gb2312");    

	        MimeBodyPart image = new MimeBodyPart();  
	        image.setDataHandler(new DataHandler(new FileDataSource("C:\\Users\\huang\\Desktop\\screen1.png")));  //javamail jaf  
	        image.setContentID("a"); 
	        
	        MimeMultipart mm = new MimeMultipart();  
	        mm.addBodyPart(text); 
	        mm.addBodyPart(image);
	        mm.setSubType("related");// 
	        
	        message.setContent(mm);
	        message.saveChanges();
//	        message.writeTo(new FileOutputStream("C:\\Users\\huang\\Desktop\\1.eml")); 
	        // 发送邮件
	        Transport.send(message);
	    }
	   public static void main(String[] args) throws MessagingException {
	        // 配置发送邮件的环境属性
	        final Properties props = new Properties();
	        /*
	         * 可用的属性： mail.store.protocol / mail.transport.protocol / mail.host /
	         * mail.user / mail.from
	         */
	        // 表示SMTP发送邮件，需要进行身份验证
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.host", "smtp.sina.com");
	        // 发件人的账号
	        props.put("mail.user", "javatest0930@sina.com");
	        // 访问SMTP服务时需要提供的密码
	        props.put("mail.password", "javatest");

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
	        InternetAddress form = new InternetAddress(
	                props.getProperty("mail.user"));
	        message.setFrom(form);

	        // 设置收件人
	        InternetAddress to = new InternetAddress("244992140@qq.com");
	        message.setRecipient(RecipientType.TO, to);

//	        // 设置抄送
//	        InternetAddress cc = new InternetAddress("huangxinyu.tsinghua@gmail.com");
//	        message.setRecipient(RecipientType.CC, cc);
//
//	        // 设置密送，其他的收件人不能看到密送的邮件地址
//	        InternetAddress bcc = new InternetAddress("aaaaa@163.com");
//	        message.setRecipient(RecipientType.CC, bcc);

	        // 设置邮件标题
	        message.setSubject("javatest");

	        // 设置邮件的内容体
	        message.setContent("<a href='http://www.github.com'>测试的HTML邮件</a>", "text/html;charset=UTF-8");

	        // 发送邮件
	        Transport.send(message);
	    }
	}

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
 * ʵ���ʼ����͹���
 * @author huang
 *
 */
public class MailTest {
	   public void send() throws Exception {
		   final Properties props = new Properties();
	        /*
	         * ���õ����ԣ� mail.store.protocol / mail.transport.protocol / mail.host /
	         * mail.user / mail.from
	         */
	        // ��ʾSMTP�����ʼ�����Ҫ���������֤
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.host", "smtp.sina.com");
	        // �����˵��˺�
	        props.put("mail.user", "javatest0930@sina.com");
	        // ����SMTP����ʱ��Ҫ�ṩ������
	        props.put("mail.password", "javatest");

	        // ������Ȩ��Ϣ�����ڽ���SMTP���������֤
	        Authenticator authenticator = new Authenticator() {
	            @Override
	            protected PasswordAuthentication getPasswordAuthentication() {
	                // �û���������
	                String userName = props.getProperty("mail.user");
	                String password = props.getProperty("mail.password");
	                return new PasswordAuthentication(userName, password);
	            }
	        };
	        // ʹ�û������Ժ���Ȩ��Ϣ�������ʼ��Ự
	        Session mailSession = Session.getInstance(props, authenticator);
	        // �����ʼ���Ϣ
	        MimeMessage message = new MimeMessage(mailSession);
	        // ���÷�����
	        InternetAddress form = new InternetAddress(props.getProperty("mail.user"));
	        message.setFrom(form);

	        // �����ռ���
	        InternetAddress to = new InternetAddress("244992140@qq.com");
	        message.setRecipient(RecipientType.TO, to);
	        
	        // �����ʼ�����
	        message.setSubject("javatest");

	        // �����ʼ���������
//	        �����ʼ�ͼƬ1  
	        MimeBodyPart text = new MimeBodyPart(); 
	        text.setContent("���ʼ�Ϊϵͳ�Զ�����<img src='cid:a'>", "text/html;charset=gb2312");    

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
	        // �����ʼ�
	        Transport.send(message);
	    }
	   public static void main(String[] args) throws MessagingException {
	        // ���÷����ʼ��Ļ�������
	        final Properties props = new Properties();
	        /*
	         * ���õ����ԣ� mail.store.protocol / mail.transport.protocol / mail.host /
	         * mail.user / mail.from
	         */
	        // ��ʾSMTP�����ʼ�����Ҫ���������֤
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.host", "smtp.sina.com");
	        // �����˵��˺�
	        props.put("mail.user", "javatest0930@sina.com");
	        // ����SMTP����ʱ��Ҫ�ṩ������
	        props.put("mail.password", "javatest");

	        // ������Ȩ��Ϣ�����ڽ���SMTP���������֤
	        Authenticator authenticator = new Authenticator() {
	            @Override
	            protected PasswordAuthentication getPasswordAuthentication() {
	                // �û���������
	                String userName = props.getProperty("mail.user");
	                String password = props.getProperty("mail.password");
	                return new PasswordAuthentication(userName, password);
	            }
	        };
	        // ʹ�û������Ժ���Ȩ��Ϣ�������ʼ��Ự
	        Session mailSession = Session.getInstance(props, authenticator);
	        // �����ʼ���Ϣ
	        MimeMessage message = new MimeMessage(mailSession);
	        // ���÷�����
	        InternetAddress form = new InternetAddress(
	                props.getProperty("mail.user"));
	        message.setFrom(form);

	        // �����ռ���
	        InternetAddress to = new InternetAddress("244992140@qq.com");
	        message.setRecipient(RecipientType.TO, to);

//	        // ���ó���
//	        InternetAddress cc = new InternetAddress("huangxinyu.tsinghua@gmail.com");
//	        message.setRecipient(RecipientType.CC, cc);
//
//	        // �������ͣ��������ռ��˲��ܿ������͵��ʼ���ַ
//	        InternetAddress bcc = new InternetAddress("aaaaa@163.com");
//	        message.setRecipient(RecipientType.CC, bcc);

	        // �����ʼ�����
	        message.setSubject("javatest");

	        // �����ʼ���������
	        message.setContent("<a href='http://www.github.com'>���Ե�HTML�ʼ�</a>", "text/html;charset=UTF-8");

	        // �����ʼ�
	        Transport.send(message);
	    }
	}

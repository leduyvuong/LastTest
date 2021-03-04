package com.jsp.shopaoquan.Service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Component;

@Component
public class SendMail {
	 public void sendText(String email, String name) throws AddressException, MessagingException {
		    Properties mailServerProperties;
		    Session getMailSession;
		    MimeMessage mailMessage;
		 
		    // Step1: setup Mail Server
		    mailServerProperties = System.getProperties();
		    mailServerProperties.put("mail.smtp.port", "587");
		    mailServerProperties.put("mail.smtp.auth", "true");
		    mailServerProperties.put("mail.smtp.starttls.enable", "true");
		 
		    // Step2: get Mail Session
		    getMailSession = Session.getDefaultInstance(mailServerProperties, null);
		    mailMessage = new MimeMessage(getMailSession);
		 
		    mailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(email)); //Thay abc bằng địa chỉ người nhận
		 
		    // Bạn có thể chọn CC, BCC
//		    generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress("cc@gmail.com")); //Địa chỉ cc gmail
		 
		 
		    mailMessage.setSubject("Xác nhận đơn hàng");
		    mailMessage.setText("Xin cảm ơn quý khách đã ủng hộ shop"
		    		+ "Click vào đường link để xác nhận dưới : "
		    		+ "https://brandclothes.herokuapp.com/payment?id="+name);
		 
		    // Step3: Send mail
		    Transport transport = getMailSession.getTransport("smtp");
		 
		    // Thay your_gmail thành gmail của bạn, thay your_password thành mật khẩu gmail của bạn
		    transport.connect("smtp.gmail.com", "thaonganxinhdep1234@gmail.com", "Leduyvuong060899"); 
		    transport.sendMessage(mailMessage, mailMessage.getAllRecipients());
		    transport.close();
		  }
	 public void sendCode(String email, int code) throws AddressException, MessagingException {
		    Properties mailServerProperties;
		    Session getMailSession;
		    MimeMessage mailMessage;
		 
		    // Step1: setup Mail Server
		    mailServerProperties = System.getProperties();
		    mailServerProperties.put("mail.smtp.port", "587");
		    mailServerProperties.put("mail.smtp.auth", "true");
		    mailServerProperties.put("mail.smtp.starttls.enable", "true");
		 
		    // Step2: get Mail Session
		    getMailSession = Session.getDefaultInstance(mailServerProperties, null);
		    mailMessage = new MimeMessage(getMailSession);
		 
		    mailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(email)); //Thay abc bằng địa chỉ người nhận
		 
		    // Bạn có thể chọn CC, BCC
//		    generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress("cc@gmail.com")); //Địa chỉ cc gmail
		 
		 
		    mailMessage.setSubject("Your code");
		    mailMessage.setText("Your code :" + code);
		 
		    // Step3: Send mail
		    Transport transport = getMailSession.getTransport("smtp");
		 
		    // Thay your_gmail thành gmail của bạn, thay your_password thành mật khẩu gmail của bạn
		    transport.connect("smtp.gmail.com", "thaonganxinhdep1234@gmail.com", "Leduyvuong060899"); 
		    transport.sendMessage(mailMessage, mailMessage.getAllRecipients());
		    transport.close();
		  }
}

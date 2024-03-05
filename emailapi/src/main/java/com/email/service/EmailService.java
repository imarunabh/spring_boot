package com.email.service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	public boolean sendEmail(String subject, String message,String to) {
		boolean f=false;
		//rest of the code
		String from = "arunabhsharma54321@gmail.com";
		
		//Variable for gmail
		String host = "smtp.gmail.com";
		
		//get the system properties
		Properties properties = System.getProperties();
		
		System.out.println("PROPERTIES"+properties);
		
		//setting up important imformation
		
		//host set
		properties.put("mail.smtp.host",host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth","true");
		
		// Step 1 : to get the session object....
		javax.mail.Session session =Session.getInstance(properties, new javax.mail.Authenticator() {
				@Override
				protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
			return new javax.mail.PasswordAuthentication("arunabhsharma54321@gmail.com","aaannn@@@54321");
		}
		});
		
		session.setDebug(true);
		
		MimeMessage m = new MimeMessage(session);
		
		try {
			m.setFrom(from);
			m.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
			m.setSubject(subject);
			m.setText(message);
			Transport.send(m);
			System.out.println("Sent Success");
			f=true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return f;
	
}
}

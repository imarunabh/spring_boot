package com.lcwd;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	System.out.println("preparing to send message");
        String message="Hello, Dear this is message for security check";
        String subject="CodersArea:Confirmation";
        String to="arunabhsharma88450@gmail.com";
        String from="arunabhsharma54321@gmail.com";
        
        sendEmail(message,subject,to,from);
        
    }

    //this is responsible for sending email...
	private static void sendEmail(String message, String subject, String to, String from) {
		
		//variable for gmail
		String host="smtp.gmail.com";
		
		//get the system properties
		Properties properties = System.getProperties();
		System.out.println("PROPERTIES:"+properties);
		
		//setting important information to properties object
		
		
		//host set
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.host","465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");
		
		Session session = Session.getInstance(properties, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication("21btrcs263@jainuniversity.ac.in","aaannn@@54321");
			}
			
		});
		session.setDebug(true);
		
		//step 2 
		//compose the message
		MimeMessage m = new MimeMessage(session);
		try {
			m.setFrom(from);
			//adding receipient
			m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			
			//adding subject to messaage
			m.setSubject(subject);
			
			//adding text to message
			m.setText(message);
			
			//send 
			//step 3: send the message using Transport class 
			Transport.send(m);
			System.out.println("Send Successfully");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		
	}
}

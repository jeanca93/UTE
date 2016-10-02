package edu.servicios.util;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {

	private static Properties mailServerProperties;
	private static Session getMailSession;
	private static MimeMessage generateMailMessage;
	private static final String host = "smtp.gmail.com";
	private static final String user = "gaha.uefgs.duran@gmail.com";
	private static final String password = "adminGAHA2016";
	
	public static void generateAndSendEmail(String lAsunto, String lCuerpo, String copia, String ... lListaEmails) throws AddressException, MessagingException, UnsupportedEncodingException {
		// Step1
		System.out.println("\n1st ===> setup Mail Server Properties..");
		mailServerProperties = System.getProperties();
		mailServerProperties.put("mail.smtp.port", "587");
		mailServerProperties.put("mail.smtp.auth", "true");
		mailServerProperties.put("mail.smtp.starttls.enable", "true");
		System.out.println("Mail Server Properties have been setup successfully..");
	 
		// Step2
		System.out.println("\n2nd ===> get Mail Session..");
		getMailSession = Session.getDefaultInstance(mailServerProperties, null);
		generateMailMessage = new MimeMessage(getMailSession);
		
		InternetAddress[] toAddress = new InternetAddress[lListaEmails.length];

	    // To get the array of addresses
	    for( int i=0; i < lListaEmails.length; i++ ) { // changed from a while loop
	        toAddress[i] = new InternetAddress(lListaEmails[i]);
	    }

	    for( int i=0; i < toAddress.length; i++) { // changed from a while loop
	    	generateMailMessage.addRecipient(Message.RecipientType.TO, toAddress[i]);
	    }
		
		//generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(para));
		generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress(copia));
		generateMailMessage.setFrom(new InternetAddress(user, "Unidad Educativa Federico González Suarez"));
		generateMailMessage.setSubject(lAsunto);
		String emailBody = lCuerpo;
		generateMailMessage.setContent(emailBody, "text/html");
		System.out.println("Mail Session has been created successfully..");
	
		// Step3
		System.out.println("\n3rd ===> Get Session and Send mail");
		Transport transport = getMailSession.getTransport("smtp");
	
		// Enter your correct gmail UserID and Password
		// if you have 2FA enabled then provide App Specific Password a esto me refiero
		transport.connect(host, user, password);
		transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
		transport.close();
	}
	
	public static void main(String args[]) throws AddressException, MessagingException {
		try {
			generateAndSendEmail("Generación de Horarios terminada", "<p style='color:#00008B;'>Estimado admin,</p><br><p style='color:#00008B;text-align:justify;'>El proceso de generación automática de horarios ha finalizado satisfactoriamente. Favor dirigirse al sistema para visualizar la información correspondiente.</p>", "duberjesus@gmail.com", "jeanca93@gmail.com");
			System.out.println("\n===> Your Java Program has just sent an Email successfully. Check your email..");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

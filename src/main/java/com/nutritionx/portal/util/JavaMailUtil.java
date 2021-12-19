package com.nutritionx.portal.util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaMailUtil {

	public static void sendMail(String recipient, int emailType, String userToken) {

		System.out.println("Preparing Email");
		Properties properties = new Properties();

		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");

		String senderAccountEmail = "nutritionxlatam@gmail.com";
		String senderAccountPassword = "Mejorad0"; 

		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(senderAccountEmail, senderAccountPassword);
			}
		});

		Message message = prepareMessage(session, senderAccountEmail, recipient, emailType, userToken);

		try {
			Transport.send(message);

		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Email Send sucessfully");

	}

	private static Message prepareMessage(Session session, String senderAccountEmail, String recipient, int emailType,
			 String userToken) {

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(senderAccountEmail));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
			
			String htmlCode ="";
			
			switch (emailType) {
			case 1:// Self-Reg email content prep
				message.setSubject("Bienvenido a NutritionX");
				htmlCode = "<table style=\"border-spacing: 0; width: 100%; background-color: #ebeeef; table-layout: fixed\"><tbody><tr><td style=\"padding: 20px; vertical-align: top; font-family: Arial, sans-serif; border-width: 0;\"><center><table style=\"border-spacing: 0; width: 650px;\"><tbody><tr><td style=\"border-width: 0; background-color: #FFF;; padding-left: 40px; padding-top: 30px;\"><img src=\"https://raw.githubusercontent.com/arielrbv/universidadutiles/main/svgexport-27.png\" alt=\"logo\" width=\"260\" height=\"53\" ></td></tr><tr><td style=\"padding-right: 40px; padding-bottom: 5px; padding-left: 40px; background-color: #FFF;\"><hr style=\"background-color: #FFF; border-bottom-width: 1px; border-top-width: 0; border-right-width: 0; border-left-width: 0; border-bottom-style: solid; border-color: #c0ddcb;\"></td></tr><tr><td style=\"background-color: #FFF; text-align: center; padding-top: 30px;\"><img src=\"https://assets.lumen.com/is/image/Lumen/icon-ddos-hyper-support-desktop?$PNG$&amp;Creativeid=a09a5c2d-71b9-4934-88b5-b6ed084b37d3\" alt=\"Collaboration\"></td></tr><tr><td style=\"padding-top: 30px; padding-right: 40px; padding-bottom: 15px; padding-left: 40px; background-color: #FFF;font-size: 25px; color: #689c54;\"><strong>El Equipo de </strong><img style=\"height:22px;\" src=\"https://raw.githubusercontent.com/arielrbv/universidadutiles/main/solologo.png\"/><strong> te da la bienvenida </strong></td></tr><tr><td style=\"padding-top: 0px; padding-right: 40px;padding-bottom: 35px; padding-left: 40px; background-color: #FFF; font-size: 14px; color: #6b6b6b;\">Activa tu cuenta con el siguiente enlace y comienza a disfrutar de los beneficios que te ofrecemos.</td></tr><tr><td style=\"padding-top: 0px;padding-right: 40px;padding-bottom: 0px;padding-left: 40px;background-color: #FFF;font-size: 17.5px;color: #6b6b6b;text-align:center;\"><a href=\"#toReplace\"><b>Activa tu cuenta</b></a></td></tr><tr><td style=\"padding-top: 30px; padding-right: 30px; padding-bottom: 5px; padding-left: 30px; background-color: #FFF;\"><hr style=\"background-color:#FFF; border-bottom-width: 1px; border-top-width: 0; border-right-width: 0; border-left-width: 0; border-bottom-style: dotted;border-color: #6b6b6b;\"></td></tr><tr><td style=\"padding-top: 5px; padding-right: 40px; padding-bottom: 30px; padding-left: 40px; background-color:#FFF;color: #6b6b6b; font-size: 12px;\">Este correo ha sido generado de forma automática. Por favor, no responda.</td></tr></tbody></table></center></td></tr></tbody></table>";
				htmlCode=htmlCode.replace("#toReplace", "http://localhost:8080/AccountActivation?userEmail="+recipient+"&userToken="+userToken);
				break;
			case 2:// PassReset email content prep
				break;
			default:
				break;
			}
			// message.setText("first email sent!! \n so let see how is it looks like");
			message.setContent(htmlCode, "text/html");
			return message;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}

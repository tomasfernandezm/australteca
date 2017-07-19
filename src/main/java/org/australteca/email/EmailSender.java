package org.australteca.email;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by tomi on 12/06/17.
 */
public class EmailSender {

    private final String username="australteca@gmail.com";
    private final String password="lab123456789";
    private Properties props = new Properties();
    private Session session;

    public EmailSender() {
        setupProperties();
        setSession();
    }

    private void setupProperties(){
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
    }

    private void setSession(){
        session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }

    public void send(String recipient, String subject, String text, boolean isHTML){
        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject(subject);
            if(isHTML) message.setContent(text, "text/html; charset=utf-8");
            else message.setText(text);

            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}

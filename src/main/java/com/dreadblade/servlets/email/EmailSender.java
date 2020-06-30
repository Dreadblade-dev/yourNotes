package com.dreadblade.servlets.email;

import com.dreadblade.servlets.email.utils.MailProperties;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class EmailSender {
    private static Properties properties;
    private static EmailSender instance;

    private EmailSender(Properties properties) {
        this.properties = properties;
    }

    public static void sendEmail(String toAddress, String subject, String message) throws AddressException, MessagingException {
        if (instance == null)
            instance = new EmailSender(MailProperties.getProperties());

        final String username = properties.getProperty("mail.username");
        final String password = properties.getProperty("mail.password");

        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        };

        Session session = Session.getInstance(properties, authenticator);

        Message msg = new MimeMessage(session);

        msg.setFrom(new InternetAddress(username));
        InternetAddress[] toAdresses = {
                new InternetAddress(toAddress)
        };
        msg.setRecipients(Message.RecipientType.TO, toAdresses);
        msg.setSubject(subject);
        msg.setSentDate(new Date());
        msg.setContent(message, "text/html");
        Transport.send(msg);
    }
}

package ecommerce.util;

import java.util.Date;
import java.util.Properties;
import ecommerce.data.CustomerDB;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailUtility {
    public static void sendEmail(String host, String port,
                                 final String userName, final String password, String toAddress)
            throws AddressException, MessagingException {

        // sets SMTP server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2"); // Specify the SSL protocol
        // creates a new session with an authenticator
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        };

        Session session = Session.getInstance(properties, auth);

        // creates a new e-mail message
        Message msg = new MimeMessage(session);

        msg.setFrom(new InternetAddress(userName));
        InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
        msg.setRecipients(Message.RecipientType.TO, toAddresses);

        //Random string forgot password
        String res = CustomerDB.generateRandomString();
        CustomerDB.changePassword(toAddress, res);

        msg.setSubject(res);
        msg.setSentDate(new Date());
        msg.setContent(res, "text/plain; charset=UTF-8");

        // sends the e-mail
        Transport.send(msg);
    }
}


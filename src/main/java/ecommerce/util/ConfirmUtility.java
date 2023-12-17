package ecommerce.util;

import ecommerce.business.CustomerEntity;
import ecommerce.business.LineitemsEntity;
import ecommerce.business.ProductEntity;
import ecommerce.data.ProductDB;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class ConfirmUtility {
    public static void sendEmail(String host, String port,
                                 final String userName, final String password,
                                 String toAddress, CustomerEntity customer,
                                 List<LineitemsEntity> lineitems)
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

        msg.setSubject("Confirm Email");
        msg.setSentDate(new Date());
        String content = "Confirm mail: \n\n " +
                "-------------------------------------------------";

        //Content User vs Shop
        content +="\nCustomer info:"
                + "\nName: " + customer.getUsername()
                + "\nUsername: " + customer.getUsername()
                + "\nPhone Number: " + customer.getCustomerphonenumber()
                + "\nAddress: " + customer.getCustomeraddress() + "\n";

        //Content
        content += "\nDanh sách sản phẩm:";
        for (LineitemsEntity line: lineitems) {
            content += "\nName: " + line.getProduct().getProducttittle()
                                + "; SL: " + line.getAmount();

            //Cập nhật SL bán ra
            updateProduct(line.getProduct().getProductid(), line.getAmount());
        }
        content += "\n\nTOTAL COST: " + ProductDB.calculateTotal(lineitems);

        msg.setContent(content, "text/plain; charset=UTF-8");

        // sends the e-mail
        Transport.send(msg);
    }
    public static void updateProduct(Long productId, int amount) {
        EntityManager em = DBUtil.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            ProductEntity product = em.find(ProductEntity.class, productId);
            if (product != null) {
                product.setProducttotalselling(
                        product.getProducttotalselling() + amount);
                em.merge(product);
                trans.commit();
            } else {
                System.out.println("Sản phẩm không tồn tại trong cơ sở dữ liệu.");
            }
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }
}


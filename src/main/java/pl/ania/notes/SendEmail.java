package pl.ania.notes;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Component
public class SendEmail {


    void send(String body) {
        String to = "szwajkowskilukasz@gmail.com";
        String to2 = "szwajkowskaa@gmail.com";
        String host = "smtp.gmail.com";
        final String username = "szwajkowskaa@gmail.com";
        final String password = "ADGJ97531";

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        Properties properties = new Properties();


        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.ssl.enable", "true");

        mailSender.setJavaMailProperties(properties);



//        mailSender.setUsername("szwajkowskaa@gmail.com");
//        mailSender.setPassword("ADGJ97531");

        Session session = Session.getInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        MimeMessage mimeMessage = new MimeMessage(session);

        try {

            mimeMessage.setFrom(new InternetAddress("szwajkowskaa@gmail.com"));
            mimeMessage.setSubject("subject");
            mimeMessage.setText(body);
            mimeMessage.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(to));
            mimeMessage.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(to2));
        } catch (MessagingException exception) {
            exception.printStackTrace();
        }

        try {
            Transport.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }


//        javax.mail.internet.MimeMessage mimeMessage = mailSender.createMimeMessage();
//        MimeMessageHelper helper;
//        try {
//            helper = new MimeMessageHelper(mimeMessage, true);
//            helper.setFrom(new InternetAddress("szwajkowskaa@gmail.com"));
//            helper.setSubject("subject");
//            helper.setText(body, true);
//            helper.addTo(to);
//        } catch (MessagingException exception) {
//            exception.printStackTrace();
//        }
//        mailSender.send(mimeMessage);

//        Message message = new MimeMessage(session);
//        message.setFrom(new InternetAddress("from-email@gmail.com"));
//        message.setRecipients(Message.RecipientType.TO,
//                InternetAddress.parse("to-email@gmail.com"));
//        message.setSubject("Testing Subject");
//        message.setText("Dear Mail Crawler,"
//                + "\n\n No spam to my email, please!");
//
//        Transport.send(message);
//
//        System.out.println("Done");




    }
}

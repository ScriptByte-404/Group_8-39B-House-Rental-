package controller;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailUtility {

    public static boolean sendOTP(String recipientEmail, String otp) {

        final String senderEmail = "safalbegha5@gmail.com";
        final String senderPassword = "fiqn dqdc cfjl tjiy";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
            new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(senderEmail, senderPassword);
                }
            });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(
                Message.RecipientType.TO,
                InternetAddress.parse(recipientEmail)
            );
           // Professional, secure style modeled after top tech companies
String emailBody = "Hello,\n\n"+ "We received a request to reset the password for your account. "
        + "Please use the following Verification Code (OTP) to complete the process:\n\n"
        + "Your OTP is: " + otp + "\n\n"
        + "For your security, this code is strictly confidential and will expire in 5 minutes.\n\n"
        + "If you did not request a password reset, please ignore this email or contact support if you have concerns.\n\n"
        + "Best regards,\n"
        + "The Project Team";

message.setSubject("Security: Password Reset Verification Code");
message.setText(emailBody);

            Transport.send(message);
            return true;

        } catch (MessagingException e) {
            System.out.println(e);
            return false;
        }
    }
}
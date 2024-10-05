package com.digi.email;

import jakarta.mail.Authenticator;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;

import java.util.Properties;

public class EmailSender {
    private static final String PASSWORD = "lkwkufrhccmiebuq";
    private static final String USERNAME = "bookingsystembook@yandex.ru";

    public static void sendEmail(String to, String subject, String text) {
        String host = "smtp.yandex.ru";
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.enable", "true");

        Session session = Session.getInstance(props,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(USERNAME, PASSWORD);
                    }
                });
        try {
            EmailConfig.send(session, USERNAME, to, subject, text);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

}

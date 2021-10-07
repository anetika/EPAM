package edu.epam.webproject.util;

import edu.epam.webproject.controller.command.CommandType;
import edu.epam.webproject.controller.command.RequestParameter;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MailSender {
    private MimeMessage message;
    private static final String RESOURCE_FILE = "\\mail.properties";
    private static final Properties properties = new Properties();

    private static final String REGISTRATION_SUBJECT = "";
    private static final String FORGET_PASSWORD_SUBJECT = "";
    private static final String EQUALS_SIGN = "=";
    private static final String QUESTION_SIGN = "?";
    private static final String AMPERSAND_SIGN = "&";
    private static final String MAIL_DEPLOYMENT = "mail.deployment";

    static {
        try(InputStream stream = MailSender.class.getClassLoader().getResourceAsStream(RESOURCE_FILE)) {
            properties.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendRegistrationMessage(String email) {
        try {
            initMessage(REGISTRATION_SUBJECT, buildRegisterLink(email), email);
            Transport.send(message);
        } catch (MessagingException e) {
            //log
        }
    }

    public void sendForgetPasswordMessage(String email){
        try{
            initMessage(FORGET_PASSWORD_SUBJECT, buildForgetPasswordLink(email), email);
            Transport.send(message);
        } catch (MessagingException e) {
            //log
        }
    }

    private void initMessage(String mailSubject, String mailText, String sendToEmail) throws MessagingException {
        Session mailSession = SessionFactory.createSession(properties);
        message = new MimeMessage(mailSession);
        message.setSubject(mailSubject);
        message.setContent(mailText, "text/html");
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(sendToEmail));
    }

    private String buildRegisterLink(String email){
        StringBuilder builder = new StringBuilder();
        builder.append(properties.getProperty(MAIL_DEPLOYMENT));
        builder.append(RequestParameter.CONTROLLER);
        builder.append(QUESTION_SIGN);
        builder.append(RequestParameter.COMMAND);
        builder.append(EQUALS_SIGN);
        builder.append(CommandType.ACTIVATE_ACCOUNT_COMMAND.toString().toLowerCase());
        builder.append(AMPERSAND_SIGN);
        builder.append(RequestParameter.EMAIL);
        builder.append(EQUALS_SIGN);
        builder.append(email);
        return builder.toString();
    }

    private String buildForgetPasswordLink(String email){
        StringBuilder builder = new StringBuilder();
        builder.append(properties.getProperty(MAIL_DEPLOYMENT));
        builder.append(RequestParameter.CONTROLLER);
        builder.append(QUESTION_SIGN);
        builder.append(RequestParameter.COMMAND);
        builder.append(EQUALS_SIGN);
        builder.append(CommandType.GO_TO_CHANGE_USER_PASSWORD_PAGE_COMMAND.toString().toLowerCase());
        builder.append(AMPERSAND_SIGN);
        builder.append(RequestParameter.EMAIL);
        builder.append(EQUALS_SIGN);
        builder.append(email);
        return builder.toString();
    }
}

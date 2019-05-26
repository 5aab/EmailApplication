package com.mail.app.rest;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Arrays;
import java.util.Date;

@Service
@AllArgsConstructor
@Slf4j
public class EmailSenderImpl implements EmailSender {

    private JavaMailSender javaMailSender;
    private TemplateEngine templateEngine;
    private Context context;

    @Override
    public void sendEmail() {
        SimpleMailMessage msg = new SimpleMailMessage();
        String imageResourceName= "sample.jpeg";
        msg.setTo("ish.mahajan2@outlook.com", "ish.mahajan2@outlook.com");
        msg.setSubject("Testing from Spring Boot");
        msg.setText("Hi There! this is a test email");
        javaMailSender.send(msg);
    }

    @Override
    public void sendHtmlEmail() throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8"); // true = multipart
        String imageResourceName= "robo.png";
        Address[] addresses=new Address[1];
        addresses[0]= new InternetAddress("ish.mahajan2@outlook.com");
        messageHelper.setTo("ish.mahajan2@outlook.com");
        messageHelper.setSubject("Testing from Spring Boot with email template");
        context.setVariable("name", "5aab");
        context.setVariable("teamName", "5aab");
        context.setVariable("subscriptionDate", new Date());
        context.setVariable("hobbies", Arrays.asList("Cinema", "Sports", "Music"));
        context.setVariable("imageResourceName", imageResourceName); // so that we can reference it from HTML
        String body = templateEngine.process("intimation", context);

        messageHelper.setText(body,true);
        javaMailSender.send(mimeMessage);
    }
}

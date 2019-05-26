package com.mail.app.rest;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.mail.MessagingException;

@Controller
@Slf4j
@AllArgsConstructor
public class EmailController {

    private EmailSender emailSender;

    @GetMapping("/api/send/text/email")
    public void sendTextEmail(){
        emailSender.sendEmail();
    }

    @GetMapping("/api/send/html/email")
    public void sendHtmlEmail() throws MessagingException {
        emailSender.sendHtmlEmail();
    }
}

package com.mail.app.rest;

import javax.mail.MessagingException;

public interface EmailSender {
    void sendEmail();

    void sendHtmlEmail() throws MessagingException;
}

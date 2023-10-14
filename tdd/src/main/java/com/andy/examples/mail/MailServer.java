package com.andy.examples.mail;

public interface MailServer {
    void sendEmail(String recipient, String subject, String text);
}

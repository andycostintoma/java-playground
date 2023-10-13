package com.andy.examples;

public interface MailServer {
    void sendEmail(String recipient, String subject, String text);
}

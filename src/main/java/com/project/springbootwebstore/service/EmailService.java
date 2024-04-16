package com.project.springbootwebstore.service;

import jakarta.mail.MessagingException;

import java.io.IOException;

public interface EmailService {

    void sendSimpleMessage(String to, String subject, String text) throws MessagingException, IOException;
}

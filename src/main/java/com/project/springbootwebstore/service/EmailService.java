package com.project.springbootwebstore.service;

import jakarta.mail.MessagingException;

import java.io.IOException;

public interface EmailService {

    void send(Email email) throws MessagingException, IOException;
}

package com.project.springbootwebstore.service;

public record Email(
        String from,
        String to,
        String subject,
        String confirmationUrl,
        String recipientName
) {

}
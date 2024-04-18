package com.project.springbootwebstore.service.impl;

import com.project.springbootwebstore.service.Email;
import com.project.springbootwebstore.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.IOException;

import static com.nimbusds.jose.util.StandardCharset.*;
import static org.springframework.mail.javamail.MimeMessageHelper.*;

@Component
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender emailSender;
    private final TemplateEngine templateEngine;

    @Override
    @Async
    @SneakyThrows
    public void send(final Email email) {

        final Context ctx = new Context(LocaleContextHolder.getLocale());
        ctx.setVariable("email",email.to());
        ctx.setVariable("name",email.recipientName());
        ctx.setVariable("logo", "templates/images/logo.png");
        ctx.setVariable("url", email.confirmationUrl());

        final String htmlContent = templateEngine.process("email", ctx);

        final MimeMessage mimeMessage = emailSender.createMimeMessage();

        final MimeMessageHelper helper;
        helper = new MimeMessageHelper(mimeMessage, MULTIPART_MODE_MIXED_RELATED, UTF_8.name());
        helper.setTo(email.to());
        helper.setSubject(email.subject());
        helper.setFrom(email.from());
        helper.setText(htmlContent, true);

        ClassPathResource clr = new ClassPathResource("templates/images/logo.png");

        helper.addInline("logo", clr, "image/png");

        emailSender.send(mimeMessage);
    }

}
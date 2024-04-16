package com.project.springbootwebstore.service.impl;

import com.nimbusds.jose.util.Base64;
import com.project.springbootwebstore.service.EmailService;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender emailSender;
    private final TemplateEngine templateEngine;

    @SneakyThrows
    public void sendSimpleMessage(
            String to, String subject, String text) {
        Resource logo = new ClassPathResource("static/images/iphone14.jpg");
        Resource logoResource = new ClassPathResource("static/images/iphone14.jpg");
        Path logoPath = logoResource.getFile().toPath();

        // Encode the file to Base64
        byte[] logoBytes = Files.readAllBytes(logoPath);
        String logoBase64 = Base64.encode(logoBytes).toString();

        final Context ctx = new Context();
        ctx.setVariable("name", "John");
        ctx.setVariable("subscriptionDate", new Date());
        ctx.setVariable("hobbies", Arrays.asList("Cinema", "Sports", "Music"));
        ctx.setVariable("logo2", "static/images/hfw.jpg");
        ctx.setVariable("logo64",logoBase64);// so that we can reference it from HTML

        final String htmlContent = this.templateEngine.process("email-inlineimage.html", ctx);
        final MimeMessage mimeMessage = this.emailSender.createMimeMessage();
        final MimeMessageHelper message =
                new MimeMessageHelper(mimeMessage, true, "UTF-8"); // true = multipart

//        FileSystemResource res = new FileSystemResource(new File("static/images/Logo.svg"));
//        Resource logo = new ClassPathResource("static/images/Logo.svg");

        message.setSubject("Example HTML email with inline image");
        message.setFrom("hepyep636@gmail.com");
        message.setTo(to);
        message.setText(htmlContent, true);
        // invoke after set text
        message.addInline("Logo", logo, "image/jpeg");

        emailSender.send(mimeMessage);
    }
}
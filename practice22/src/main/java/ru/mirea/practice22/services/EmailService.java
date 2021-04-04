package ru.mirea.practice22.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailService {
    private JavaMailSender javaMailSender;

    @Async
    void sendEmail(String subject, String text) {
        log.info("Send message consist of subject - {}, text - {}", subject, text);
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("oleg.victorove4@yandex.ru");
        msg.setSubject(subject);
        msg.setText(text);
        javaMailSender.send(msg);
    }

    @Autowired
    public void setJavaMailSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }
}

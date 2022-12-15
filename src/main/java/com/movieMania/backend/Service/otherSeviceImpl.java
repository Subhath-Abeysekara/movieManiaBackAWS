package com.movieMania.backend.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class otherSeviceImpl implements otherService{


    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendMails(String toEmail, String subject, String body) {
        SimpleMailMessage massage = new SimpleMailMessage();
        massage.setFrom("moviemania691@gmail.com");
        massage.setTo(toEmail);
        massage.setSubject(subject);
        massage.setText(body);

        javaMailSender.send(massage);
    }
}

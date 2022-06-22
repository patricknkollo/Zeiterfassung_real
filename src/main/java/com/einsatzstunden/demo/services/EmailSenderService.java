package com.einsatzstunden.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {

  @Autowired
  private JavaMailSender javaMailSender;

  public void sendMail(String toEmail,
      String subject,
      String body
      ) {

    SimpleMailMessage message = new SimpleMailMessage();
    message.setFrom("nkollopatrick@gmail.com");
    message.setTo(toEmail);
    message.setText(body);
    message.setSubject(subject);

   /* MimeMessage mimeMessage=javaMailSender.createMimeMessage();
    MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage,true);
    mimeMessageHelper.setFrom("nkollopatrick@gmail.com");
    mimeMessageHelper.setTo(toEmail);
    mimeMessageHelper.setText(body);
    mimeMessageHelper.setSubject(subject);*/


    javaMailSender.send(message);
    System.out.println("Mail sent successfully...");


  }
}

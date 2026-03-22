package com.spring.backend.Service.MailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class Mail {
    
    @Autowired private JavaMailSender jSender;

    public void sendMail(String email, String code, String name){
        SimpleMailMessage smm = new SimpleMailMessage();
        smm.setTo(email);
        smm.setSubject("Your OTP Code is : " + code.charAt(0) + "XXXXX");
        smm.setText("Hello User ["+ name + "]\n" + //
                        "Your verification code is: "+ code +"\n" + //
                        "This code is valid for 5 minutes.\n" + //
                        "Please do not share this code with anyone.\n" + //
                        "If you did not request this code, please ignore this email.\n" + //
                        "Thank you,\n" + //
                        "The Support Team\n" + //
                        "");
        jSender.send(smm);
    }
}

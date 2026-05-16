package com.spring.backend.Service.Mail;

import com.spring.backend.Interface.MailInterface;
import com.spring.backend.Modal.UserModal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService implements MailInterface {

    @Autowired private JavaMailSender javaMailSender;

    @Override
    public void SendMail(UserModal userModal, String Code) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(userModal.getEmail());
        simpleMailMessage.setSubject("Your OTP Code starts with : " + Code.charAt(0) + "XXXXX");
        simpleMailMessage.setText("Hello User" + userModal.getName() + "\n" +
                "Your verification code is: " + Code + "\n" +
                "This code is valid for 5 minutes.\n" +
                "Please do not share this code with anyone.\n" +
                "If you did not request this code, please ignore this email.\n" +
                "Thank you,\n" +
                "The Support Team");
        javaMailSender.send(simpleMailMessage);
        simpleMailMessage = null;
    }

    public void forgetPasswordMail(String email, String url){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject("Reset Your Password");
        simpleMailMessage.setText("Hello,\n" +
                "\n" +
                "We received a request to reset your password for your account.\n" +
                "\n" +
                "Click the link below to create a new password:\n" +
                "\n" +
                url + "\n" +
                "\n" +
                "This link will expire in 15 minutes for security reasons.\n" +
                "\n" +
                "If you did not request a password reset, you can safely ignore this email. Your password will remain unchanged.\n" +
                "\n" +
                "Thank you,\n" +
                "Support Team");
        javaMailSender.send(simpleMailMessage);
        simpleMailMessage = null;
    }
}

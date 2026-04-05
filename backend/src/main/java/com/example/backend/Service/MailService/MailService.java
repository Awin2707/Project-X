package com.example.backend.Service.MailService;

import com.example.backend.Interface.MailInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService implements MailInterface {

    @Autowired private JavaMailSender javaMailSender;

    @Override
    public void SenMail(String email, String name, String Code) {
        SimpleMailMessage smm = new SimpleMailMessage();
        smm.setTo(email);
        smm.setSubject("Your OTP Code starts with : " + Code.charAt(0) + "XXXXX");
        smm.setText("Hello User "+ name + "\n" +
                "Your verification code is: "+ Code +"\n" +
                "This code is valid for 5 minutes.\n" +
                "Please do not share this code with anyone.\n" +
                "If you did not request this code, please ignore this email.\n" +
                "Thank you,\n" +
                "The Support Team\n");
        javaMailSender.send(smm);
    }

    @Override
    public void ResetSendMail(String token, String email) {
        SimpleMailMessage smm = new SimpleMailMessage();
        smm.setTo(email);
        smm.setSubject("Password Reset Request");
        smm.setText("Hello,\n" + //
                        "\n" + //
                        "We received a request to reset your password.\n" + //
                        "\n" + //
                        "If you made this request, please click the link below to reset your password:\n" + //
                        "\n" + //
                        "🔗 "+ "https://dev-saving-cost.netlify.app/resetPass/"+ token +"\n" + //
                        "\n" + //
                        "This link will expire in {{EXPIRY_TIME}} minutes for security reasons.\n" + //
                        "\n" + //
                        "If you did not request a password reset, please ignore this email. Your account will remain safe and unchanged.\n" + //
                        "\n" + //
                        "For any assistance, feel free to contact our support team.\n" + //
                        "\n" + //
                        "Best regards,\n" + //
                        "Your Application Team");
        javaMailSender.send(smm);
    }
}

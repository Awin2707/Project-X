package com.spring.backend.Service.UserService;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.backend.Data.DataModal;
import com.spring.backend.Entity.OtpEntity;
import com.spring.backend.Entity.UserEntity;
import com.spring.backend.Exception.UserException;
import com.spring.backend.Interface.LoginInterface;
import com.spring.backend.Interface.OtpVerificationInterface;
import com.spring.backend.Interface.RegisterInterface;
import com.spring.backend.Modal.LoginModal;
import com.spring.backend.Modal.OtpModal;
import com.spring.backend.Modal.RegisterModal;
import com.spring.backend.Repo.OtpRepo;
import com.spring.backend.Repo.UserRepo;
import com.spring.backend.Service.Base64.Base64;
import com.spring.backend.Service.MailService.Mail;
import com.spring.backend.Service.Token.GenerateToken;

@Service
public class UserService implements RegisterInterface, OtpVerificationInterface, LoginInterface {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private OtpRepo otpRepo;
    @Autowired
    private Base64 base64;
    @Autowired
    private GenerateToken generateToken;
    @Autowired
    private Mail mail;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserEntity createAccount(DataModal dataModal) {
        String key = "myKeySecret";
        ObjectMapper obj = new ObjectMapper();
        String Token = generateToken.generateToken();
        String Code = "";
        try {
            String json = base64.decode(dataModal.getData(), key);
            System.out.println("DECODED JSON: " + json);
            RegisterModal registerModal = obj.readValue(json, RegisterModal.class);
            if (registerModal != null) {
                obj = null;
                UserEntity users = userRepo.findByEmail(registerModal.getEmail());
                if (users == null) {
                    UserEntity user = new UserEntity(null, registerModal.getEmail(), registerModal.getName(),
                            passwordEncoder.encode(registerModal.getPass()), false, LocalDateTime.now(), false, Token,
                            false);
                    OtpEntity otps = new OtpEntity(null, registerModal.getEmail(), Token, Code,
                            LocalDateTime.now().plusMinutes(5));
                    userRepo.save(user);
                    otpRepo.save(otps);
                    mail.sendMail(registerModal.getEmail(), Code, registerModal.getName());
                } else if (users != null && !users.isVerify()) {
                    users.setName(registerModal.getName());
                    users.setPass(registerModal.getPass());
                    users.setUtoken(Token);
                    users.setLocalDateTime(LocalDateTime.now());
                    userRepo.save(users);
                    OtpEntity otp = otpRepo.findByEmail(registerModal.getEmail());
                    if (otp != null) {
                        otp.setCode(Code);
                        otp.setToken(Token);
                        otp.setLocalDateTime(LocalDateTime.now().plusMinutes(5));
                        otpRepo.save(otp);
                    } else {
                        OtpEntity otps = new OtpEntity(null, registerModal.getEmail(), Token, Code,
                                LocalDateTime.now().plusMinutes(5));
                        otpRepo.save(otps);
                    }
                } else {
                    throw new UserException("this email id already exists !");
                }
            } else {
                throw new UserException("data missing !");
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new UserException("encryaption failed !");
        }
        return null;
    }

    @Override
    public UserEntity otpVerification(DataModal dataModal) {
        String key = "myKeySecret";
        ObjectMapper objs = new ObjectMapper();
        try {
            String json = base64.decode(dataModal.getData(), key);
            OtpModal otp = objs.readValue(json, OtpModal.class);
            if (otp != null) {
                objs = null;
                OtpEntity otps = otpRepo.findByEmail(otp.getEmail());
                UserEntity user = userRepo.findByEmail(otps.getEmail());
                if ((otps.getToken().equals(otps.getToken()))) {
                    if (otps.getCode().equals(otp.getCode())) {
                        if (otps.getLocalDateTime().isBefore(LocalDateTime.now())) {
                            user.setVerify(true);
                            user.setLogin(true);
                            user.setUtoken(null);
                            return userRepo.save(user);
                        } else {
                            throw new UserException("OTP have been experied !");
                        }
                    } else {
                        throw new UserException("invalid OTP Code");
                    }
                } else {
                    throw new UserException("you are the hacker !");
                }
            }
        } catch (Exception e) {
            throw new UserException("encryaption failed !");
        }
        return null;
    }

    @Override
    public UserEntity resendOtp(DataModal dataModal) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'resendOtp'");
    }

    @Override
    public UserEntity Login(DataModal dataModal) {
        String key = "myKeySecret";
        ObjectMapper objs = new ObjectMapper();
        try {
            String json = base64.decode(dataModal.getData(), key);
            LoginModal login = objs.readValue(json, LoginModal.class);
            if (login != null) {
                UserEntity users = userRepo.findByEmail(login.getEmail());
                if (users != null && passwordEncoder.matches(login.getPass(), users.getPass())) {
                    return users;
                } else {
                    throw new UserException("invalid user email or password !");
                }
            } else {
                throw new UserException("encryption failed !");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}

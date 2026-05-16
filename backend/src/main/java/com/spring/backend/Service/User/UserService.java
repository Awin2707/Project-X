package com.spring.backend.Service.User;

import com.spring.backend.Entity.ForgetPasswordEntity;
import com.spring.backend.Entity.OtpEntity;
import com.spring.backend.Entity.UserEntity;
import com.spring.backend.Exception.UserException;
import com.spring.backend.Interface.ForgetPasswordInterface;
import com.spring.backend.Interface.UserInterface;
import com.spring.backend.Modal.*;
import com.spring.backend.Repo.ForgetPasswordRepo;
import com.spring.backend.Repo.OtpRepo;
import com.spring.backend.Repo.UserRepo;
import com.spring.backend.Service.Mail.MailService;
import com.spring.backend.Service.Token.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService implements UserInterface, ForgetPasswordInterface {

    @Autowired private UserRepo userRepo;
    @Autowired private OtpRepo otpRepo;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private TokenService tokenService;
    @Autowired private MailService mailService;
    @Value("${spring.url}")
    private String url_Link;
    @Autowired private ForgetPasswordRepo forgetPasswordRepo;

    @Override
    public UserEntity createAccount(UserModal userModal) {
        UserEntity users = userRepo.findByEmail(userModal.getEmail());
        String token = tokenService.generateToken();;
        if (users != null && users.isVerify()){
            throw new UserException("this email id already exists !");
        }else if (users != null && !users.isVerify()){
            users.setName(userModal.getName());
            users.setPass(passwordEncoder.encode(userModal.getPass()));
            users.setToken(token);
            users.setLogin(LocalDateTime.now());
            boolean isSend = OtpSend(userModal, token);
            if (isSend){
                return userRepo.save(users);
            }else {
                boolean Send = OtpSend(userModal, token);
                return userRepo.save(users);
            }
        }else {
            UserEntity user = new UserEntity(null, userModal.getEmail(), userModal.getName(), passwordEncoder.encode(userModal.getPass()), false, LocalDateTime.now(),token);
            boolean isSend = OtpSend(userModal, token);
            if (isSend){
                return userRepo.save(user);
            }else {
                boolean Send = OtpSend(userModal, token);
                return userRepo.save(user);
            }
        }
    }

    @Override
    public boolean OtpSend(UserModal email, String token) {
        OtpEntity otp = otpRepo.findByEmail(email.getEmail());
        String code = tokenService.generateCode();
        if (otp != null){
            otp.setToken(token);
            otp.setCode(code);
            otp.setOptValid(LocalDateTime.now().plusMinutes(5));
            otpRepo.save(otp);
            mailService.SendMail(email, code);
            return true;
        }else {
            OtpEntity Otp = new OtpEntity(null, code, email.getEmail(), token, LocalDateTime.now().plusMinutes(5));
            otpRepo.save(Otp);
            mailService.SendMail(email, code);
            return true;
        }
    }

    @Override
    public OtpEntity otpVerification(OtpModal otpModal) {
        UserEntity users = userRepo.findByEmail(otpModal.getEmail());
        if (users != null){
            OtpEntity otps = otpRepo.findByToken(users.getToken());
            if (otps != null){
                if (otps.getCode().equals(otpModal.getCode())){
                    if (otps.getOptValid().isAfter(LocalDateTime.now())){
                        users.setVerify(true);
                        userRepo.save(users);
                        otps.setToken(null);
                        return otpRepo.save(otps);
                    }else {
                        throw new UserException("Otp have been expired !");
                    }
                }else {
                    throw new UserException("invalid OTP Code !");
                }
            }else {
                throw new UserException("invalid credential Token !");
            }
        }else {
            throw new UserException("invalid credentials !");
        }
    }

    @Override
    public UserEntity login(LoginModal loginModal) {
        UserEntity users = userRepo.findByEmail(loginModal.getEmail());
        if (users != null && passwordEncoder.matches(loginModal.getPass(), users.getPass())){
            return users;
        }
        throw new UserException("invalid email id or password !");
    }

    @Override
    public String sendForgetPassword(ForgetPasswordModal forgetPasswordModal) {
        try{
            UserEntity users = userRepo.findByEmail(forgetPasswordModal.getEmail());
            if (users != null){
                users = null;
                String token = tokenService.generateToken();
                String url = url_Link.concat(token);
                ForgetPasswordEntity emails = forgetPasswordRepo.findByEmail(forgetPasswordModal.getEmail());
                if (emails == null){
                    ForgetPasswordEntity forgetPasswordEntity = new ForgetPasswordEntity(null, forgetPasswordModal.getEmail(), forgetPasswordModal.getFingerPrint(), null, token, LocalDateTime.now().plusMinutes(10), null, false);
                    mailService.forgetPasswordMail(forgetPasswordModal.getEmail(), url);
                    forgetPasswordRepo.save(forgetPasswordEntity);
                    forgetPasswordEntity = null;
                    return url;
                }else {
                    emails.setExpire_Time(LocalDateTime.now().plusMinutes(10));
                    emails.setRequestFingerPrint(forgetPasswordModal.getFingerPrint());
                    emails.setToken(token);
                    emails.setVerify(false);
                    emails.setSetFingerPrint(null);
                    mailService.forgetPasswordMail(forgetPasswordModal.getEmail(), url);
                    forgetPasswordRepo.save(emails);
                    emails = null;
                    return url;
                }
            }else {
                throw new UserException("user email id not exists !");
            }
        }catch (Exception e){
            System.out.println(e.getMessage() + "msg");
        }
        return null;
    }

    @Override
    public boolean resetPassword(ResetPassword resetPassword) {
        ForgetPasswordEntity passwordEntity = forgetPasswordRepo.findByToken(resetPassword.getToken());
        if (passwordEntity != null){
            if (passwordEntity.getToken().equals(resetPassword.getToken())){
                if (!passwordEntity.isVerify()){
                    if (passwordEntity.getExpire_Time().isAfter(LocalDateTime.now())) {
                        passwordEntity.setPassword(resetPassword.getPassword());
                        passwordEntity.setSetFingerPrint(resetPassword.getSetFingerPrint());
                        UserEntity users = userRepo.findByEmail(passwordEntity.getEmail());
                        if(users != null){
                            users.setPass(passwordEncoder.encode(resetPassword.getPassword()));
                            userRepo.save(users);
                        }else {
                            throw new UserException("you are the hacker !");
                        }
                        passwordEntity.setVerify(true);
                        forgetPasswordRepo.save(passwordEntity);
                        return true;
                    }else {
                        throw new UserException("link have been expired !");
                    }
                }else {
                    throw new UserException("this link already have been used !");
                }
            }else {
                throw new UserException("invalid url !");
            }
        }else {
            throw new UserException("Some think went wrong !");
        }
    }

    @Override
    public boolean isValidLink(String token) {
        ForgetPasswordEntity passwordEntity = forgetPasswordRepo.findByToken(token);
        if (passwordEntity != null){
            if (passwordEntity.isVerify()){
                return false;
            }else {
                return true;
            }
        }else {
            throw new UserException("invalid url !");
        }
    }
}

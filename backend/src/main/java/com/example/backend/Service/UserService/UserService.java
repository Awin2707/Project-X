package com.example.backend.Service.UserService;

import com.example.backend.Base64.Base64;
import com.example.backend.Data.DataModal;
import com.example.backend.Entity.OtpEntity;
import com.example.backend.Entity.ResetEntity;
import com.example.backend.Entity.UserEntity;
import com.example.backend.Exception.UserException;
import com.example.backend.Interface.ForgetPassword;
import com.example.backend.Interface.LoginInterface;
import com.example.backend.Interface.OtpInterface;
import com.example.backend.Interface.RegisterInterface;
import com.example.backend.Modal.ExistsModal;
import com.example.backend.Modal.LoginModal;
import com.example.backend.Modal.OtpModal;
import com.example.backend.Modal.RegisterModal;
import com.example.backend.Modal.ResetModal;
import com.example.backend.Modal.ResetPassword;
import com.example.backend.Repo.OtpRepo;
import com.example.backend.Repo.ResetRepo;
import com.example.backend.Repo.UserRepo;
import com.example.backend.Service.MailService.MailService;
import com.example.backend.Service.Token.TokenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UserService implements RegisterInterface, OtpInterface, LoginInterface, ForgetPassword {

    @Autowired
    private Base64 base64;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private OtpRepo otpRepo;
    @Autowired
    private MailService mailService;
    @Autowired
    private ResetRepo resetRepo;

    @Override
    public UserEntity createAccount(DataModal dataModal) {
        try {
            String Token = tokenService.generateToken();
            String Otp = tokenService.generateOtp();
            String json = base64.decodeDate(dataModal.getData());
            ObjectMapper obj = new ObjectMapper();
            RegisterModal register = obj.readValue(json, RegisterModal.class);
            obj = null;
            if (register != null) {
                UserEntity user = userRepo.findByEmail(register.getEmail());
                if (user == null) {
                    UserEntity users = new UserEntity(null, register.getEmail(),
                            passwordEncoder.encode(register.getPass()), register.getName(), Token, false, false, null,
                            null, register.getBrowser());
                    OtpEntity otp = new OtpEntity(null, register.getEmail(), Token, Otp, register.getBrowser());
                    otpRepo.save(otp);
                    mailService.SenMail(register.getEmail(), register.getName(), Otp);
                    return userRepo.save(users);
                } else if (!user.isVerify()) {
                    user.setName(register.getName());
                    user.setPass(passwordEncoder.encode(register.getPass()));
                    user.setBrowserType(register.getBrowser());
                    user.setToken(Token);
                    OtpEntity otp = new OtpEntity(null, register.getEmail(), Token, Otp, register.getBrowser());
                    mailService.SenMail(register.getEmail(), register.getName(), Otp);
                    otpRepo.save(otp);
                    return userRepo.save(user);
                } else {
                    throw new UserException("this account already exists !");
                }
            }
            throw new UserException("encryption failed !");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.printf("msg : " + e.getMessage());
        }
        return null;
    }

    @Override
    public UserEntity otpVerification(DataModal dataModal) {
        try {
            String Json = base64.decodeDate(dataModal.getData());
            ObjectMapper obj = new ObjectMapper();
            OtpModal otp = obj.readValue(Json, OtpModal.class);
            obj = null;
            if (otp != null) {
                OtpEntity otps = otpRepo.findByEmail(otp.getEmail());
                UserEntity users = userRepo.findByEmail(otps.getEmail());
                if (otps != null) {
                    if (otps.getCode().equals(otp.getCode())) {
                        if (otp.getBrowser().equals(otps.getBrowser())) {
                            users.setCreatedTime(LocalDateTime.now());
                            users.setVerify(true);
                            return userRepo.save(users);
                        }
                    } else {
                        throw new UserException("invalid Otp Code !");
                    }
                }
                throw new UserException("encryption failed !");
            }
        } catch (Exception e) {
            System.out.println("msg  : " + e.getMessage());
        }
        return null;
    }

    @Override
    public UserEntity login(DataModal dataModal) {
        try {
            String json = base64.decodeDate(dataModal.getData());
            ObjectMapper obj = new ObjectMapper();
            LoginModal login = obj.readValue(json, LoginModal.class);
            if (login != null) {
                UserEntity user = userRepo.findByEmail(login.getEmail());
                if (user != null && passwordEncoder.matches(login.getPass(), user.getPass())) {
                    return user;
                }
                throw new UserException("invalid user name or password !");
            }
            throw new UserException("encryption failed !");
        } catch (Exception e) {
            System.out.printf("msg : " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean SendMail(DataModal dataModal) {
        try {
            String encode = base64.decodeDate(dataModal.getData());
            ObjectMapper obj = new ObjectMapper();
            ResetModal reset = obj.readValue(encode, ResetModal.class);
            if (reset != null) {
                UserEntity user = userRepo.findByEmail(reset.getEmail());
                if (user != null) {
                    UUID uuid = UUID.randomUUID();
                    String token = uuid.toString();
                    ResetEntity re = resetRepo.findByEmail(reset.getEmail());
                    if (re == null) {
                        ResetEntity resetEntity = new ResetEntity(null, reset.getEmail(), token,
                                LocalDateTime.now().plusMinutes(30), false);
                        resetRepo.save(resetEntity);
                        mailService.ResetSendMail(token, reset.getEmail());
                        return true;
                    } else if (re != null) {
                        re.setToken(token);
                        re.setIsExperiedToken(LocalDateTime.now().plusMinutes(30));
                        re.setPassReset(false);
                        resetRepo.save(re);
                        mailService.ResetSendMail(token, reset.getEmail());
                        return true;
                    }
                } else {
                    throw new UserException("user email id not exists !");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("msg :" + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean isAlreadyReset(DataModal dataModal) {
        try {
            String json = base64.decodeDate(dataModal.getData());
            ObjectMapper obj = new ObjectMapper();
            ExistsModal existsModal = obj.readValue(json, ExistsModal.class);
            obj = null;
            if (existsModal != null) {
                ResetEntity re = resetRepo.findByToken(existsModal.getToken());
                if (re != null) {
                    if (re.getIsExperiedToken().isAfter(LocalDateTime.now())) {
                        if (re.isPassReset()) {
                            throw new UserException("invalid link !");
                        } else {
                            return true;
                        }
                    } else {
                        throw new UserException("link have been expired !");
                    }
                } else {
                    throw new UserException("invalid user !");
                }
            } else {
                throw new UserException("enc failed !");
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new UserException(e.getMessage());
        }
    }

    @Override
    public boolean resetPassword(DataModal dataModal) {
        try {
            String Json = base64.decodeDate(dataModal.getData());
            ObjectMapper obj = new ObjectMapper();
            ResetPassword res = obj.readValue(Json, ResetPassword.class);
            if (res != null) {
                ResetEntity resPass = resetRepo.findByToken(res.getToken());
                if (resPass != null) {
                    if (resPass.getIsExperiedToken().isAfter(LocalDateTime.now())) {
                        if (res.getPassword().equals(res.getNewPassword())) {
                            UserEntity ue = userRepo.findByEmail(resPass.getEmail());
                            ue.setPass(passwordEncoder.encode(res.getNewPassword()));
                            userRepo.save(ue);
                            return true;
                        } else {
                            throw new UserException("password not match !");
                        }
                    } else {
                        throw new UserException("link has been expired !");
                    }
                } else {
                    throw new UserException("invallid url !");
                }
            } else {
                throw new UserException("enc failed !");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new UserException(e.getMessage());
        }
    }
}

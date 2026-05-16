package com.spring.backend.Interface;

import com.spring.backend.Entity.OtpEntity;
import com.spring.backend.Entity.UserEntity;
import com.spring.backend.Modal.LoginModal;
import com.spring.backend.Modal.OtpModal;
import com.spring.backend.Modal.UserModal;

public interface UserInterface {

    UserEntity createAccount(UserModal userModal);

    boolean OtpSend(UserModal email, String token);

    OtpEntity otpVerification(OtpModal otpModal);

    UserEntity login(LoginModal loginModal);
}

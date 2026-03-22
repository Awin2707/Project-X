package com.spring.backend.Interface;

import com.spring.backend.Data.DataModal;
import com.spring.backend.Entity.UserEntity;

public interface OtpVerificationInterface {
    
    public UserEntity otpVerification(DataModal dataModal);

    public UserEntity resendOtp(DataModal dataModal);
}

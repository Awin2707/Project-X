package com.spring.backend.Interface;

import com.spring.backend.Modal.ForgetPasswordModal;
import com.spring.backend.Modal.ResetPassword;

public interface ForgetPasswordInterface {

    String sendForgetPassword(ForgetPasswordModal forgetPasswordModal);

    boolean resetPassword(ResetPassword resetPassword);

    boolean isValidLink(String token);
}

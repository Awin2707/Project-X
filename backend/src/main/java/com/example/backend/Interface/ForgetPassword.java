package com.example.backend.Interface;

import com.example.backend.Data.DataModal;

public interface ForgetPassword {

    boolean SendMail(DataModal dataModal);

    boolean isAlreadyReset(DataModal dataModal);

    boolean resetPassword(DataModal dataModal);
}

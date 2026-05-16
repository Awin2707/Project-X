package com.spring.backend.Interface;

import com.spring.backend.Modal.UserModal;

public interface MailInterface {

    void SendMail(UserModal userModal, String Code);
}

package com.spring.backend.Interface;

import com.spring.backend.Data.DataModal;
import com.spring.backend.Entity.UserEntity;

public interface LoginInterface {
    
    public UserEntity Login(DataModal dataModal);
}

package com.example.backend.Interface;

import com.example.backend.Data.DataModal;
import com.example.backend.Entity.UserEntity;

public interface LoginInterface {

    UserEntity login(DataModal dataModal);
}

package com.example.backend.UserDetails;

import com.example.backend.Entity.UserEntity;
import com.example.backend.Exception.UserException;
import com.example.backend.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CustomUserDetails implements UserDetailsService {

    @Autowired private UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepo.findByEmail(username);
        if (user == null){
            throw new UserException("user email id did not exists !");
        }
        return new User(user.getEmail(), user.getPass(), new ArrayList<>());
    }
}

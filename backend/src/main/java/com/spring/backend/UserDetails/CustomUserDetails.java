package com.spring.backend.UserDetails;

import com.spring.backend.Entity.UserEntity;
import com.spring.backend.Repo.UserRepo;
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
        UserEntity ud = userRepo.findByEmail(username);
        if (ud == null){
            throw new UsernameNotFoundException("this user email id not exists !");
        }
        return new User(ud.getEmail(), null, new ArrayList<>());
    }
}

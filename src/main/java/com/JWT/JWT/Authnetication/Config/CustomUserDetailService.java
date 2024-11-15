package com.JWT.JWT.Authnetication.Config;

import Dto.UserDto;
import com.JWT.JWT.Authnetication.Entity.User;
import com.JWT.JWT.Authnetication.Exception.UserNotFoundException;
import com.JWT.JWT.Authnetication.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
    
    @Autowired
    private UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User userIsNotFound = userRepo.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User is not found"));
        return (UserDetails) userIsNotFound;
    }
}

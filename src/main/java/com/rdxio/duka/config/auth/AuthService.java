/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.rdxio.duka.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.rdxio.duka.config.auth.dtos.SignUpDto;
import com.rdxio.duka.users.entities.User;
import com.rdxio.duka.users.enums.UserRole;
import com.rdxio.duka.users.repositories.UserRepository;

/**
 *
 * @author 7X
 */
@Service
public class AuthService implements UserDetailsService {


    @Autowired 
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        var user = userRepository.findByLogin(username);
        return user;
    }

    public UserDetails signUp(SignUpDto signUpDto) throws Exception {
        if (userRepository.findByLogin(signUpDto.login()) != null) {
            throw new Exception("User already exists");
        }
       
        String encryptedPass = new BCryptPasswordEncoder().encode(signUpDto.password());
        User user = new User(signUpDto.login(), encryptedPass, UserRole.valueOf(signUpDto.role()));
        return userRepository.save(user);
    }

}

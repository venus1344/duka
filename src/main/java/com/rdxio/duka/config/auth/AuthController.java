/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.rdxio.duka.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rdxio.duka.config.auth.dtos.JwtDto;
import com.rdxio.duka.config.auth.dtos.SignInDto;
import com.rdxio.duka.config.auth.dtos.SignUpDto;
import com.rdxio.duka.users.entities.User;

import jakarta.validation.Valid;


/**
 *
 * @author 7X
 */
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody @Valid SignUpDto signUpDto) {
        try {
            authService.signUp(signUpDto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody @Valid SignInDto signInDto) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(signInDto.login(), signInDto.password());
        var auth = authenticationManager.authenticate(usernamePassword);
        var token = tokenProvider.generateToken((User)auth.getPrincipal());
        return ResponseEntity.status(HttpStatus.OK).body(new JwtDto(token));
    }
}


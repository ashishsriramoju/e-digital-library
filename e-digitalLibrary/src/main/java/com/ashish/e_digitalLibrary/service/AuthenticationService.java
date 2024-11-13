package com.ashish.e_digitalLibrary.service;

import com.ashish.e_digitalLibrary.dto.AuthDto;
import com.ashish.e_digitalLibrary.entity.Member;
import com.ashish.e_digitalLibrary.exception.InvalidUserCredentialsException;
import com.ashish.e_digitalLibrary.repository.MemberRepository;
import com.ashish.e_digitalLibrary.util.JwtUtil;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class AuthenticationService {

    private final MyUserDetailsService myUserDetailsService;

    private final JwtUtil jwtUtil;

    public AuthenticationService(MyUserDetailsService myUserDetailsService, JwtUtil jwtUtil) {
        this.myUserDetailsService = myUserDetailsService;
        this.jwtUtil = jwtUtil;
    }

    public UserDetails login(AuthDto authDto){
        UserDetails user = this.myUserDetailsService.loadUserByUsername(authDto.getUsername());
        if(user  == null){
            throw new InvalidUserCredentialsException("wrong username and password");
        }
        return user;
    }

    public String register(AuthDto authDto){
        System.out.println("in the authentication service " + authDto.getUsername());
        UserDetails user = this.myUserDetailsService.loadUserByUsername(authDto.getUsername());
        return this.jwtUtil.generateToken(user);
    }
}

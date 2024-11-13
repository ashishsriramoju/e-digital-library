package com.ashish.e_digitalLibrary.controller;


import com.ashish.e_digitalLibrary.dto.AuthDto;
import com.ashish.e_digitalLibrary.entity.Member;
import com.ashish.e_digitalLibrary.service.AuthenticationService;
import jakarta.persistence.PostRemove;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> validateUsername( @RequestBody AuthDto authDto) {
        UserDetails user = this.authenticationService.login(authDto);
        System.out.println("user is " + user.getUsername());
        if(user == null){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser( @RequestBody AuthDto authDto) {
        String token = this.authenticationService.register(authDto);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }
}

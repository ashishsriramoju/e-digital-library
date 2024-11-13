package com.ashish.e_digitalLibrary.dto;


import lombok.*;
import org.springframework.stereotype.Service;

@Builder
@With
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthDto {

    private String username;

    private String password;
}

package com.ashish.e_digitalLibrary.service;


import com.ashish.e_digitalLibrary.entity.Member;
import com.ashish.e_digitalLibrary.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private MemberRepository memberRepository;

    @Autowired
    public MyUserDetailsService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

//    List<User> users = Arrays.asList(new
//            User("user", "{noop}password", Collections.singletonList(new SimpleGrantedAuthority("ROLE_MEMBER")))
//    , new User("user2", "{noop}pass2", Collections.singletonList(
//            new SimpleGrantedAuthority("ROLE_LIBRARIAN"))));

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("int my user detaild service");
        Optional<Member> member  = memberRepository.findMemberByUsername(username);
        if(member.isEmpty()){
            throw new UsernameNotFoundException("user not found");
        }
        return User.builder()
                .username(member.get().getUsername())
                .password(member.get().getPassword())
                .roles(member.get().getRole())
                .build();
    }
}

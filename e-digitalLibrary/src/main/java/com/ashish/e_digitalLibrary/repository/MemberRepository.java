package com.ashish.e_digitalLibrary.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashish.e_digitalLibrary.entity.Member;

public interface MemberRepository extends JpaRepository<Member, UUID>{

    Optional<Member> findMemberByUsername(String username);

}

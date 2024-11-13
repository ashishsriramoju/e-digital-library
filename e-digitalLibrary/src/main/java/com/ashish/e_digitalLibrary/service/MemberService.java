package com.ashish.e_digitalLibrary.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashish.e_digitalLibrary.entity.Member;
import com.ashish.e_digitalLibrary.repository.MemberRepository;


@Service
public class MemberService {
	
	private final  MemberRepository memberRepository;
	
	@Autowired
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository  = memberRepository;
	}
	
	
	public Member addMember(Member member) {
		return this.memberRepository.save(member);
	}
	
	public List<Member> getAllMembers() {
		return this.memberRepository.findAll();
	}
	
	public Member getMemberById(UUID id) {
		Optional<Member> optionalMember =  this.memberRepository.findById(id);
		return optionalMember.orElse(null);
	}
	
	public Member updateMember(Member newMember) {
		UUID id = newMember.getId();
		Optional<Member> member = this.memberRepository.findById(id);
		
		Member m2  = member.get().withId(newMember.getId())
					.withEmail(newMember.getEmail())
					.withFirstName(newMember.getFirstName())
					.withLastName(newMember.getLastName())
					.withMobileNumber(newMember.getMobileNumber())
					.withSubscription(newMember.getSubscription());
		return this.memberRepository.save(m2);
	}

}

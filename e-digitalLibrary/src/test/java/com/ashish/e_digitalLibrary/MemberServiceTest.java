package com.ashish.e_digitalLibrary;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.ashish.e_digitalLibrary.entity.Member;
import com.ashish.e_digitalLibrary.repository.MemberRepository;
import com.ashish.e_digitalLibrary.service.MemberService;

@SpringBootTest(classes= {MemberService.class})
public class MemberServiceTest {
	
	
	public final MemberService memberService;
	
	@Autowired
	public MemberServiceTest(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@MockBean
	public MemberRepository memberRepository;
	
	public static UUID id = UUID.randomUUID();
	
	static Member member = Member.builder()
			                      .id(id)
			                     .Email("ashishsriramoju25@gmail.com")
			                     .firstName("Ashish")
			                     .lastName("Sriramoju")
			                     .mobileNumber("9550112505")
			                     .subscription(Member.Subscription.ACTIVE)
			                     .build();
	
	@Test
	void addMember_whenValidMemberisgiven_outputismember() {
		Mockito.when(this.memberRepository.save(member)).thenReturn(member);
		
		Member m = this.memberService.addMember(member);
		
		org.junit.jupiter.api.Assertions.assertEquals(m, m);
	}
	
	@Test
	void getMemberById() {
		Mockito.when(this.memberRepository.findById(id)).thenReturn(Optional.of(member));
		Member m = this.memberService.getMemberById(id);
		org.junit.jupiter.api.Assertions.assertEquals(m,m);
	}

}

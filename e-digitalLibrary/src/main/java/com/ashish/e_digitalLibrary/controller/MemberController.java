package com.ashish.e_digitalLibrary.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ashish.e_digitalLibrary.entity.Member;
import com.ashish.e_digitalLibrary.service.MemberService;

@RestController
@RequestMapping("members")
public class MemberController {
	
	private final MemberService memberService;
	
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@PostMapping("/add")
	public ResponseEntity<Member> addMember(@RequestBody Member member){
		Member newMember = this.memberService.addMember(member);
		return new ResponseEntity<> (newMember, HttpStatus.CREATED);
	}
	
	@GetMapping("/list")
	public ResponseEntity<List<Member>> getList(){
		List<Member> list = this.memberService.getAllMembers();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@GetMapping("/member/{id}")
	public ResponseEntity<?> getMemberById(@PathVariable UUID id){
		Member member = this.memberService.getMemberById(id);
		return new ResponseEntity<>(member, HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> updateMember(@RequestBody Member member){
		this.memberService.updateMember(member);
		return new ResponseEntity<>(member, HttpStatus.OK);
	}

}

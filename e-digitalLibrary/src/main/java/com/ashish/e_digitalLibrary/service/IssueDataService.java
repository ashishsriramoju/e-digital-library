package com.ashish.e_digitalLibrary.service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashish.e_digitalLibrary.dto.IssueDataDto;
import com.ashish.e_digitalLibrary.entity.Book;
import com.ashish.e_digitalLibrary.entity.IssueData;
import com.ashish.e_digitalLibrary.entity.IssueData.IssueStatus;
import com.ashish.e_digitalLibrary.entity.Member;
import com.ashish.e_digitalLibrary.repository.IssueDataRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class IssueDataService {
	
	private IssueDataRepository issueDataRepository;
	
	private BookService bookService;
	
	private MemberService memberService;
	
	@Autowired
	public IssueDataService(IssueDataRepository issueDataRepository, BookService bookService, MemberService memberService) {
		this.issueDataRepository = issueDataRepository;
		this.bookService = bookService;
		this.memberService = memberService;
	}
	
	public IssueData issueDto(IssueDataDto issueDataDto) {
		Book b = this.bookService.getBookById(issueDataDto.getBook_id());
		Member m = this.memberService.getMemberById(issueDataDto.getMember_id());
		IssueData i = IssueData.builder()
				                .book(b)
				                .member(m)
				                .build();
		return this.addIssueData(i);
	}
	
	public IssueData addIssueData(IssueData issueData) {
		issueData.calculateAmountPaid();
		issueData.calculateExpirationDate();
		IssueData i = this.issueDataRepository.save(issueData);
		log.info("saved the issued for id {}, book id {} and member id {}", issueData.getId(), issueData.getBook().getId(), issueData.getMember().getId());
		return i;
	}
	
	public List<IssueData> getIssueDataById(UUID id){
		List<IssueData> list = this.issueDataRepository.findByMemberId(id);
		return list;
	}
	
	public List<IssueData> markExpiration(UUID member_id){
		List<IssueData> list  = this.issueDataRepository.findByMemberId(member_id);
		for(IssueData i : list) {
			if(i.getExpirationDate().isAfter(Instant.MIN)) {
				i.setStatus(IssueStatus.EXPIRED);
				this.issueDataRepository.save(i);
			}
		}
		return list;
	}
	
}

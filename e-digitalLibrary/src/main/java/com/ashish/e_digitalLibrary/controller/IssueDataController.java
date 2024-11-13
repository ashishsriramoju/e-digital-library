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

import com.ashish.e_digitalLibrary.dto.IssueDataDto;
import com.ashish.e_digitalLibrary.entity.IssueData;
import com.ashish.e_digitalLibrary.service.IssueDataService;

@RestController
@RequestMapping("/issue-data")
public class IssueDataController {
	
	private IssueDataService issueDataService;
	
	public IssueDataController(IssueDataService issueDataService) {
		this.issueDataService = issueDataService;
	}
	
	@PostMapping("/addIssueData")
	public ResponseEntity<IssueData> addIssueData(@RequestBody IssueDataDto issueDataDto){
		IssueData savedData = this.issueDataService.issueDto(issueDataDto);
		return new ResponseEntity<IssueData>(savedData, HttpStatus.CREATED);
	}
	
	@GetMapping("/list/{id}")
	public ResponseEntity<?> getListIssueData(@PathVariable UUID id){
		List<IssueData> list = this.issueDataService.getIssueDataById(id);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> markExpired(@PathVariable UUID id){
		List<IssueData> list = this.issueDataService.markExpiration(id);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

}

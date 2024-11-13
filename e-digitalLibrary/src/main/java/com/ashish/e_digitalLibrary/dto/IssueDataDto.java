package com.ashish.e_digitalLibrary.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IssueDataDto {
	private UUID book_id;
	private UUID member_id;
}

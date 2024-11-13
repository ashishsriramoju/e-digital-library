package com.ashish.e_digitalLibrary.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@With
@Getter
@Setter
public class Member {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private UUID id;
	
	private String firstName;
	
	private String lastName;
	
	private String mobileNumber;

	private String username;

	private String password;

	private String role;
	
	@jakarta.validation.constraints.Email
	private String Email;
	
	@Enumerated(EnumType.STRING)
	private Subscription subscription = Subscription.NOT_ACTIVE;

	public enum Subscription{
		ACTIVE,
		NOT_ACTIVE
	}
}

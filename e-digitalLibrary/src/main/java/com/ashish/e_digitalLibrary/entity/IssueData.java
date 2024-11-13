package com.ashish.e_digitalLibrary.entity;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.With;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@With
public class IssueData {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private UUID id;
	
	@ManyToOne
	@NotNull
	@JsonIgnore
	private Member member;
	
	@ManyToOne
	@NotNull
	@JsonIgnore
	private Book book;
	
	@Builder.Default
	private Instant createdAt = Instant.now();
	
	@NotNull
	private Instant expirationDate;
	
	private Double amountPaid;
	
	@Builder.Default
	private IssueStatus status = IssueStatus.Issued;
	
	@JsonProperty(value="bookId")
	public UUID getBookId() {
		return this.book.getId();
	}
	
	@JsonProperty(value="memberId")
	public UUID getMemberId() {
		return this.member.getId();
	}
	
	public Instant calculateExpirationDate() {
		this.expirationDate = this.createdAt.plus(15, ChronoUnit.DAYS);
		return this.expirationDate;
	}
	
	public Double calculateAmountPaid() {
		this.amountPaid = this.book.getPrice()*0.05;
		return this.amountPaid;
	}
	
	public enum IssueStatus{
		Issued, EXPIRED
	}

}

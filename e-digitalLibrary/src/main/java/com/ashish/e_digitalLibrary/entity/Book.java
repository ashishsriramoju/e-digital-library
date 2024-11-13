package com.ashish.e_digitalLibrary.entity;



import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Book {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private UUID id;
	
	private String name;
	
	private String author;
	
	private String description;
	
	@Column
	private boolean isbn;
	
	@Enumerated(EnumType.STRING)
	private Category category;
	
	private Double price;
	
	public enum Category{
		FICTION,
		NON_FICTION
	}
	
	
	
}

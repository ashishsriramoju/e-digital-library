package com.ashish.e_digitalLibrary.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashish.e_digitalLibrary.entity.Book;

public interface BookRepository extends JpaRepository<Book, UUID>{

}

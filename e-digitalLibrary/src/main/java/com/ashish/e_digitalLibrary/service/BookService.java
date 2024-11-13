package com.ashish.e_digitalLibrary.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashish.e_digitalLibrary.entity.Book;
import com.ashish.e_digitalLibrary.repository.BookRepository;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BookService {
	
	
	private BookRepository bookRepository;
	
	@Autowired
	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}
	
	public Book addBook(Book book) {
		Book savedBook =  bookRepository.save(book);
		System.out.println("saved book id " + savedBook.getId());
		return savedBook;
	}
	
	public List<Book> getAllBooks(){
		return bookRepository.findAll();
	}
	
	public Book getBookById(UUID id) {
		Optional<Book> b = this.bookRepository.findById(id);
		return b.get();
	}
	
}

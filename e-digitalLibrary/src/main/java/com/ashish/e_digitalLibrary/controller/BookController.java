package com.ashish.e_digitalLibrary.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ashish.e_digitalLibrary.entity.Book;
import com.ashish.e_digitalLibrary.service.BookService;
import com.ashish.e_digitalLibrary.service.RedisService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/books")
public class BookController {
	
	private final BookService bookService;
	
	private final RedisService redisService;
	
	public BookController(BookService bookService, RedisService redisService) {
		this.bookService = bookService;
		this.redisService = redisService;
	}
	
	@PostMapping("/add")
	public ResponseEntity<Book> addBook(@RequestBody Book book){
		Book savedBook = this.bookService.addBook(book);
		this.redisService.addToCache(savedBook.getName(), savedBook);
		return new ResponseEntity<> (savedBook, HttpStatus.CREATED);
	}
	
	@GetMapping("/list")
	public ResponseEntity<?> getList(){
		List<Book> list = this.bookService.getAllBooks();
		return new ResponseEntity<> (list, HttpStatus.OK);
	}
	
	@GetMapping("/{bookId}")
	public ResponseEntity<?> getBookById(@PathVariable UUID bookId){
		Book book = this.bookService.getBookById(bookId);
		System.out.println(this.redisService.getFromCache(book.getName()));
		return new ResponseEntity<>(book, HttpStatus.OK);
	}

}

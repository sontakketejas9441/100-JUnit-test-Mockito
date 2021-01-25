package com.ts.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ts.binding.Book;
import com.ts.service.BookServiceImpl;

@RestController
public class SampleController {

	@Autowired
	BookServiceImpl bookService;
	
	
	@GetMapping("/home")
	public ResponseEntity<String> welcomeMsg(){
		
		String msg = "Welcome To JUnit test demo";
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}
	
	@PostMapping(value = "/saveBookDtls", consumes = {"application/json"})
	public ResponseEntity<String> saveBookData(@RequestBody Book book){
		String msg = null;
		boolean saveBook = bookService.saveBook(book);
		
		if(saveBook) {
			 msg = "Book saved successfully";
			 return new ResponseEntity<String>(msg,HttpStatus.CREATED);
		}else {
			msg = "Book not saved successfully";
			return new ResponseEntity<String>(msg,HttpStatus.BAD_REQUEST);
		}
		
	}
}

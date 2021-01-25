package com.ts.service;

import org.springframework.stereotype.Service;

import com.ts.binding.Book;

@Service
public class BookServiceImpl {

	public boolean saveBook(Book book) {
		//DB logic to save book
		
		return true;
	}
	
	public String welcomeMsg() {
		return "Hello from Service";
	}
}

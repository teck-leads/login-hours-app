package com.techleads.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techleads.app.model.Book;

@RestController
public class BookController {

	
	@GetMapping(value = {"/books"}, produces = {"application/json", "application/xml"})
	public Book findOne() {
		Book b=new Book();
		b.setId(101);
		b.setName("Spring");
		b.setAuthor("Jashuva");
		b.setPrice(34.5d);
		return b;
	}
	
}

package com.data.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringDataDemoApplication implements CommandLineRunner {
	
	@Autowired
	private BookRepository bookRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringDataDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Book  book = new Book();
		book.setTitle("My First Book");
		book.setContent("This is the content of the first book");
		
		Book  book1 = new Book();
		book1.setTitle("My Second Book");
		book1.setContent("This is the content of the second book");
		this.bookRepository.save(book);
		this.bookRepository.save(book1);
		
		
	}

}

package com.jpa.test.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jpa.test.dao.BookRepository;
import com.jpa.test.entities.Book;

@Component
public class BookService {
	
	
//	private static List<Book> list =new ArrayList<>();
//	
//	static {
//		list.add(new Book(12,"JavaCompleteReference","XYZ"));
//		list.add(new Book(36,"Head First to Java","ABC"));
//		list.add(new Book(12963,"Thing in Java","LMN"));
//	}
	
	@Autowired
	private BookRepository bookRepository;
	
	//get all books
	public List<Book> getAllBooks(){
		List<Book> list=(List<Book>)this.bookRepository.findAll();
		return list;
	}
	
	//get single book by Id
	public Book getBookById(int id) {
		Book book = null;
		try {
//	 book=	list.stream().filter(e->e.getId()==id).findFirst().get();
		 book=this.bookRepository.findById(id);
		}catch(Exception e) {
			e.printStackTrace();
		}
	 return book;
	}
	
	public Book addBook(Book b) {
		Book result=bookRepository.save(b);
		return result;
	}
	
//	//delete Book
//	public void deleteBook(int bid) {
//		list=list.stream().filter(book->{
//			if(book.getId()!=bid) {
//				return true;
//			}
//			else {
//				return false;
//			}
//		}).collect(Collectors.toList());
//	}
	
	//delete Book
		public void deleteBook(int bid) {
//			list=list.stream().filter(book->book.getId()!=bid).collect(Collectors.toList());
			bookRepository.deleteById(bid);
		}
      
	//update the book
		public void updateBook(Book book,int bookId){
//			list.stream().map(b->{
//				if(b.getId()==bookId) {
//					b.setTitle(book.getTitle());
//					b.setAuthor(book.getAuthor());
//				}
//				
//				return b;
//			}).collect(Collectors.toList());
			book.setId(bookId);
			bookRepository.save(book);
			
		}
		
		
}

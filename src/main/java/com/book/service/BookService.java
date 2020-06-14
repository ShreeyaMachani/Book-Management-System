package com.book.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.book.dao.BookRepository;
import com.book.model.Book;

@Service
public class BookService {

	@Autowired
	BookRepository repo;

	public List<Book> getAllBooks() {
		return repo.findAll();
	}
	
	public Optional<Book> getBookById(int id) {
		Optional<Book> book = repo.findById(id);
		return book;
	}

	public Optional<Book> getBookByAuthor(String author) {
		Optional<Book> book = repo.findByAuthor(author);
		return book;
	}

	public Book saveorUpdateBook(Book book) {
		return repo.save(book);
	}

	public List<Book> deleteBookById(int id) {
		repo.deleteById(id);
		return repo.findAll();
	}

	public void deleteAllBooks() {
		repo.deleteAll();
	}

}

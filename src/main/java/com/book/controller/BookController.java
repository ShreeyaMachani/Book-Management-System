package com.book.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.book.model.Book;
import com.book.service.BookService;

@RestController
public class BookController {

	@Autowired
	BookService service;

	@GetMapping("/books")
	public List<Book> getAllBooks() {
		return service.getAllBooks();
	}

	@GetMapping("/book/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable int id) {
		Optional<Book> book = service.getBookById(id);
		if (book.isPresent()) {
			return new ResponseEntity<Book>(book.get(), HttpStatus.OK);
		}
		return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/book/author/{author}")
	public ResponseEntity<Book> getBookByAuthor(@PathVariable String author) {
		Optional<Book> book = service.getBookByAuthor(author);
		if (book.isPresent()) {
			return new ResponseEntity<Book>(book.get(), HttpStatus.OK);
		}
		return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
	}

	@PostMapping("/books")
	public ResponseEntity<Book> saveBook(@RequestBody Book book) {
		try {
			Book b = service
					.saveorUpdateBook(new Book(book.getId(), book.getName(), book.getAuthor(), book.getPrice()));
			return new ResponseEntity<Book>(b, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<Book>(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/books/{id}")
	public ResponseEntity<Book> updateBook(@RequestBody Book book, @PathVariable int id) {
		Optional<Book> b = service.getBookById(id);
		if (b.isPresent()) {
			Book obj = b.get();
			obj.setId(book.getId());
			obj.setName(book.getName());
			obj.setAuthor(book.getAuthor());
			obj.setPrice(book.getPrice());
			service.saveorUpdateBook(obj);
			return new ResponseEntity<Book>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
		}

	}

	@DeleteMapping("/book/{id}")
	public ResponseEntity<Book> deleteBook(@PathVariable int id) {
		Optional<Book> b = service.getBookById(id);
		if (b.isPresent()) {
			service.deleteBookById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/books")
	public ResponseEntity<Book> deleteAllBooks() {
		try {
			service.deleteAllBooks();
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}

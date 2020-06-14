package com.book.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.book.model.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {
	Optional<Book> findByAuthor(String author);
}

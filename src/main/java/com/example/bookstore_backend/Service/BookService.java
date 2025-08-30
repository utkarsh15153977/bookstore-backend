package com.example.bookstore_backend.Service;

import com.example.bookstore_backend.Entity.Book;
import com.example.bookstore_backend.Repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> addBooks(Book book) {
        return Collections.singletonList(bookRepository.save(book));
    }

    public Book updateBook(long id, Book book) {
        return bookRepository.save(book); // no need to save twice
    }

    public Book deleteBook(long id) {
        bookRepository.deleteById(id);
        return null;
    }
}

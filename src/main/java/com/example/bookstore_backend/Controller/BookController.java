package com.example.bookstore_backend.Controller;

import com.example.bookstore_backend.Entity.Book;
import com.example.bookstore_backend.Service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookController {
    private final BookService bookService;
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    @GetMapping
    public List<Book> getBooks() {
        return bookService.findAllBooks();
    }
    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return (Book) bookService.addBooks(book);
    }
    @PutMapping("/{id}")
    public Book  updateBook(@PathVariable long id, @RequestBody Book book) {
        return (Book) bookService.updateBook(id, book);
    }
    @DeleteMapping("/{id}")
    public Book deleteBook(@PathVariable long id) {
        return  bookService.deleteBook(id);
    }
}

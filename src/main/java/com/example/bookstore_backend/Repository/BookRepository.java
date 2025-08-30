package com.example.bookstore_backend.Repository;

import com.example.bookstore_backend.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
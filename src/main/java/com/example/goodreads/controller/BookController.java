package com.example.goodreads.controller;

import org.springframework.web.bind.annotation.*;
import java.util.*;

import com.example.goodreads.service.BookH2Service;
import com.example.goodreads.model.Book;
import org.springframework.beans.factory.annotation.Autowired;


@RestController
public class BookController {
    @Autowired
    public BookH2Service bookService;

    @DeleteMapping("/books/{bookId}")
    public void deleteBook(@PathVariable("bookId") int bookId){
        bookService.deleteBook(bookId);
    }
    
    @PutMapping("/books/{bookId}")
    public Book updateBook(@PathVariable("bookId") int bookId, @RequestBody Book book) {
        return bookService.updateBook(bookId, book);
    }

    @PostMapping("/books")
    public Book addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    @GetMapping("/books")
    public ArrayList<Book> getBooks() {
        return bookService.getBooks();
    }

    @GetMapping("/books/{bookId}")
    public Book getBookById(@PathVariable("bookId") int bookId) {
        return bookService.getBookById(bookId);
    }
}
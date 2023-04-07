package com.example.goodreads.service;
import com.example.goodreads.repository.BookRepository;
import com.example.goodreads.model.Book;
import java.util.*;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import com.example.goodreads.model.BookRowMapper;
@Service
public class BookH2Service implements BookRepository{
    @Autowired
    private JdbcTemplate db;
    @Override
    public ArrayList<Book> getBooks(){
        List<Book> booksList=db.query("SELECT * FROM book",new BookRowMapper());
        ArrayList<Book> list=new ArrayList<>(booksList);
        return list;
    }
    @Override
    public Book getBookById(int bookId){
        Book book=db.queryForObject("SELECT * FROM book WHERE id=?", new BookRowMapper(),bookId);
        return book;
    }
    @Override
    public Book addBook(Book book){
        db.update("insert into book(name,imageUrl) values(?,?)",book.getName(),book.getImageUrl());
        Book savedBook=db.queryForObject("SELECT * FROM book WHERE name=? and imageUrl=?",new BookRowMapper(),book.getName(),book.getImageUrl());
        return savedBook;
    }
    @Override
    public Book updateBook(int bookId,Book book){
        if(book.getName()!=null){
            db.update("update book set name=? where id=?",book.getName(),bookId);
        }
        if(book.getImageUrl()!=null){
            db.update("update book set imageUrl=? where id=?",book.getImageUrl(),bookId);
        }
        return getBookById(bookId);
    }
    @Override
    public void deleteBook(int bookId){
       db.update("Delete from book where id=?",bookId);
    }

}
package com.librarysystem.library.controllers;

import com.librarysystem.library.models.Book;
import com.librarysystem.library.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {
    @Autowired
    BookService bookService;
    @PostMapping("/api/books")
    public ResponseEntity<String> createBook(@RequestBody Book book){
        bookService.createBook(book);
        return new ResponseEntity("success", HttpStatus.CREATED);
    }
    @GetMapping("/api/books")
    public ResponseEntity<List<Book>> findAllBooks(){
        return new ResponseEntity<>(bookService.findAllBooks(),HttpStatus.OK);
    }
    @GetMapping("/api/books/{id}")
    public ResponseEntity<Book> findBookById(@PathVariable int id){
        return new ResponseEntity<>(bookService.findBookById(id),HttpStatus.OK);
    }

    @PutMapping("/api/books/{id}")
    public ResponseEntity<String> updateBookDetails(@PathVariable int id,@RequestBody Book book){
        bookService.updateBookDetails(id,book);
        return new ResponseEntity("success", HttpStatus.OK);
    }

    @DeleteMapping("/api/books/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable int id){
        bookService.deleteBook(id);
        return new ResponseEntity("success", HttpStatus.OK);
    }
}

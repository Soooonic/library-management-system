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
    public ResponseEntity createBook(@RequestBody Book book){
        bookService.createBook(book);
        return new ResponseEntity("Book added to the library system", HttpStatus.ACCEPTED);
    }
    @GetMapping("/api/books")
    public List<Book> findAllBooks(){
        return bookService.findAllBooks();
    }
    @GetMapping("/api/books/{id}")
    public Book findBookById(@PathVariable int id){
        return bookService.findBookById(id);
    }

    @PutMapping("/api/books/{id}")
    public ResponseEntity updateBookDetails(@PathVariable int id,@RequestBody Book book){
        bookService.updateBookDetails(id,book);
        return new ResponseEntity("Book updated in the library system", HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/api/books/{id}")
    public ResponseEntity deleteBook(@PathVariable int id){
        bookService.deleteBook(id);
        return new ResponseEntity("Book deleted from the library system", HttpStatus.ACCEPTED);
    }
}

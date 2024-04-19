package com.librarysystem.library.services;

import com.librarysystem.library.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import com.librarysystem.library.models.Book;

import java.util.List;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;
    public void createBook(Book book){
        bookRepository.save(book);
    }
    public List<Book>findAllBooks(){
        return bookRepository.findAllBooks();
    }
    public Book findBookById(int id){
        return bookRepository.findBookById(id);
    }
    public Book findBooksByTitle(String title){
        return bookRepository.findBookSByTitle(title);
    }
    public Book findBooksByISBN(String ISBN){
        return bookRepository.findBookSByISBN(ISBN);
    }
    public List<Book> findBookSByAuthor(String author){
        return bookRepository.findBookSByAuthor(author);
    }
    public void updateBookDetails(int id,Book book){
        bookRepository.updateBookDetails(id,book);
    }
    public void deleteBook(int id ){
        bookRepository.deleteBook(id);
    }



}

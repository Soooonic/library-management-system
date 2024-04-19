package com.librarysystem.library.services;

import com.librarysystem.library.models.Book;
import com.librarysystem.library.models.BorrowingRecord;
import com.librarysystem.library.models.BorrowingStatus;
import com.librarysystem.library.models.Patron;
import com.librarysystem.library.repositories.BookRepository;
import com.librarysystem.library.repositories.BorrowingRecordRepository;
import com.librarysystem.library.repositories.PatronRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static com.librarysystem.library.models.BorrowingStatus.RETURNED;

@Service
public class BorrowingRecordService {
    @Autowired
    BorrowingRecordRepository borrowingRecordRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    PatronRepository patronRepository;
    public void createBorrowingRecord(int bookId,int patronId) throws Exception{
        Book book=bookRepository.findBookById(bookId);
        Patron patron=patronRepository.findPatronById(patronId);
        if (book==null|| !book.isAvailable()){
            throw new Exception("Book is either unavailable or not present!!");
        }
        book.setAvailable(false);
        bookRepository.updateBookDetails(bookId,book);
        BorrowingRecord borrowingRecord=new BorrowingRecord();
        Date borrowingDate = new Date();
        borrowingRecord.setBorrowingDate(borrowingDate);
        borrowingRecord.setPatron(patron);
        borrowingRecord.setBook(book);
        borrowingRecord.setBorrowingStatus(BorrowingStatus.BORROWED);
        borrowingRecordRepository.save(borrowingRecord);
    }
    public void  updateBorrowingRecordDetails(int bookId,int patronId) throws Exception{
        Book book=bookRepository.findBookById(bookId);
        book.setAvailable(true);
        bookRepository.updateBookDetails(bookId,book);
        Date returnDate = new Date();
        BorrowingStatus borrowingStatus= RETURNED;
        borrowingRecordRepository.updateBorrowingRecordDetails(bookId,patronId,returnDate,borrowingStatus);
    }
}

package com.librarysystem.library.controllers;

import com.librarysystem.library.models.Book;
import com.librarysystem.library.models.BorrowingRecord;
import com.librarysystem.library.models.Patron;
import com.librarysystem.library.services.BookService;
import com.librarysystem.library.services.BorrowingRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BorrowingRecordController {
    @Autowired
    BorrowingRecordService borrowingRecordService;
    @PostMapping("/api/borrow/{bookId}/patron/{patronId}")
    public ResponseEntity createBorrowingRecord(@PathVariable ("bookId")int bookId ,@PathVariable ("patronId")int patronId)throws Exception{
        borrowingRecordService.createBorrowingRecord(bookId,patronId);
        return new ResponseEntity("Borrowing Record added to the library system", HttpStatus.ACCEPTED);
    }

    @PutMapping("/api/return/{bookId}/patron/{patronId}")
    public ResponseEntity updateBorrowingRecordDetails(@PathVariable ("bookId")int bookId ,@PathVariable ("patronId")int patronId) throws Exception{
        borrowingRecordService.updateBorrowingRecordDetails(bookId,patronId);
        return new ResponseEntity("Borrowing Record updated in the library system", HttpStatus.ACCEPTED);
    }
}

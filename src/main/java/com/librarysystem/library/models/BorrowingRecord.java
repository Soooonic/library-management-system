package com.librarysystem.library.models;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.UUID;

import static com.librarysystem.library.models.BorrowingStatus.RETURNED;

@Entity
public class BorrowingRecord {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    private int fineAmount;

    @Enumerated(EnumType.STRING)
    private BorrowingStatus borrowingStatus;

    @ManyToOne
    @JoinColumn
    private Patron patron;
    @ManyToOne
    @JoinColumn
    private Book book;

    @CreationTimestamp
    private Date borrowingDate;

    @UpdateTimestamp
    private Date returnDate;

    public BorrowingRecord(Book book, Patron patron,BorrowingStatus borrowingStatus) {
        this.borrowingStatus = borrowingStatus;
        this.patron = patron;
        this.book = book;
        this.borrowingDate=new Date();
    }

    public BorrowingRecord(){
        this.borrowingStatus= RETURNED;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFineAmount() {
        return fineAmount;
    }

    public void setFineAmount(int fineAmount) {
        this.fineAmount = fineAmount;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public BorrowingStatus getBorrowingStatus() {
        return borrowingStatus;
    }

    public void setBorrowingStatus(BorrowingStatus borrowingStatus) {
        this.borrowingStatus = borrowingStatus;
    }

    public Date getBorrowingDate() {
        return borrowingDate;
    }

    public void setBorrowingDate(Date borrowingDate) {
        this.borrowingDate = borrowingDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Patron getPatron() {
        return patron;
    }

    public void setPatron(Patron patron) {
        this.patron = patron;
    }
}

package com.librarysystem.library.repositories;

import com.librarysystem.library.models.BorrowingRecord;
import com.librarysystem.library.models.BorrowingStatus;
import com.librarysystem.library.models.Patron;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
@Transactional
public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord,Integer> {
    @Modifying
    @Query("update BorrowingRecord br set br.borrowingStatus= :#{#borrowingStatus}," +
            "br.returnDate=:#{#returnDate} where br.book.id=:#{#bookId} and br.patron.id=:#{#partonId}")
    int updateBorrowingRecordDetails(@Param("bookId")int bookId,@Param("partonId")int partonId,@Param("returnDate") Date returnDate,@Param("borrowingStatus") BorrowingStatus borrowingStatus);
}

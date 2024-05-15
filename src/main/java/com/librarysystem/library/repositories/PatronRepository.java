package com.librarysystem.library.repositories;

import com.librarysystem.library.models.Book;
import com.librarysystem.library.models.Patron;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PatronRepository extends JpaRepository<Patron,Integer>{
    @Query("select p from Patron p")
    List<Patron> findAllPatrons();
    @Query("select p from Patron p where p.id=:id")
    Patron findPatronById(@Param ("id") int id);

    @Query("select b from Patron b where b.emailId=: mail")
    List<Patron> findByMail(String mail);
    @Modifying
    @Query("update Patron p set p.emailId=:newEmail where p.emailId=:oldEmail")
    int updatePatronEmail(@Param("oldEmail") String oldEmail, @Param("newEmail") String newEmail);

    @Modifying
    @Query("update Patron p set p.emailId= :#{#patron.emailId}," +
            "p.name=:#{#patron.name}," +
            "p.age=:#{#patron.age}," +
            "p.country=:#{#patron.country} where p.id=:#{#id}")

    int updatePatronDetails(@Param("id") int id,@Param("patron") Patron patron);
    @Modifying
    @Query("delete from Patron p where p.id=:id ")
    int deletePatron(@Param("id") int id);

}

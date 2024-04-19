package com.librarysystem.library.repositories;

import com.librarysystem.library.models.Book;
import com.librarysystem.library.models.Patron;
import jakarta.transaction.Transactional;
import org.hibernate.query.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;
@Repository
@Transactional
public interface BookRepository extends JpaRepository<Book,Integer> {
    @Query("select b from Book b")
    List<Book> findAllBooks();
    @Query("select b from Book b where b.id=:id")
    Book findBookById(@Param ("id") int id);
    @Query("select b from Book b where b.authorName=:authorName")
    List<Book> findBookSByAuthor(@Param("authorName") String authorName);
    @Query("select b from Book b where b.ISBN=:ISBN")
    Book findBookSByISBN(@Param("ISBN") String ISBN);
    @Query("select b from Book b where b.title=:title")
    Book findBookSByTitle(@Param("title") String title);
    @Modifying
    @Query("update Book b set b.ISBN= :#{#book.ISBN}," +
            "b.title=:#{#book.title}," +
            "b.authorName=:#{#book.authorName}," +"b.available=:#{#book.available}," +
            "b.publicationYear=:#{#book.publicationYear} where b.id=:#{#id}")
    int updateBookDetails(@Param("id") int id,@Param("book") Book book);
    @Modifying
    @Query("delete from Book b where b.id=:id ")
    int deleteBook(@Param("id") int id);

}

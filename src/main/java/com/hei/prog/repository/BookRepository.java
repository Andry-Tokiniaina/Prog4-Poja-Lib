package com.hei.prog.repository;

import com.hei.prog.entity.Book;
import com.hei.prog.entity.enums.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {
    List<Book> findByTitle(String title);
    List<Book> findByTitleContainingIgnoreCase(String titlePart);
    List<Book> findByAuthor(String author);
    List<Book> findByAuthorContainingIgnoreCase(String authorPart);
    List<Book> findByCategory(Category category);
    List<Book> findByCategoryAndTitleContainingIgnoreCase(Category category, String titlePart);
    List<Book> findByAuthorAndCategory(String author, Category category);
    long countByCategory(Category category);
}
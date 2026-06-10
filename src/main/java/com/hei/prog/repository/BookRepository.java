package com.hei.prog.repository;

import com.hei.prog.entity.Book;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {

  Optional<Book> findByTitleIgnoreCase(String title);
}

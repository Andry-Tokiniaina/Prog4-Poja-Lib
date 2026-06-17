package com.hei.prog.repository;

import com.hei.prog.entity.BookCopy;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookCopyRepository extends JpaRepository<BookCopy, String> {
  List<BookCopy> findByBookId(String bookId);
}

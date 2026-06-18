package com.hei.prog.services;

import com.hei.prog.entity.BookCopy;
import com.hei.prog.repository.BookCopyRepository;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookCopyService {

  private final BookCopyRepository bookCopyRepository;

  public List<BookCopy> getAll() {
    return bookCopyRepository.findAll();
  }

  public List<BookCopy> getByBookId(String bookId) {
    return bookCopyRepository.findByBookId(bookId);
  }

  public BookCopy getById(String id) {
    return bookCopyRepository
        .findById(id)
        .orElseThrow(() -> new RuntimeException("BookCopy not found: " + id));
  }

  public BookCopy create(BookCopy bookCopy) {
    bookCopy.setId(UUID.randomUUID().toString());
    return bookCopyRepository.save(bookCopy);
  }

  public BookCopy update(String id, BookCopy details) {
    BookCopy existing = getById(id);
    if (existing == null)
      throw new IllegalArgumentException("bo not found");
    return bookCopyRepository.save(details);
  }

  public void delete(String id) {
    bookCopyRepository.deleteById(id);
  }
}

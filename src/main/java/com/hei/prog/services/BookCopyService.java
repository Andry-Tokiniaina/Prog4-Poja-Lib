package com.hei.prog.services;

import com.hei.prog.endpoint.exception.NotFoundException;
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

  public BookCopy getById(UUID id) {
    return bookCopyRepository.findById(id).orElseThrow(() -> new NotFoundException("Book", id));
  }

  public BookCopy create(BookCopy bookCopy) {
    return bookCopyRepository.save(bookCopy);
  }

  public BookCopy update(UUID id, BookCopy details) {
    BookCopy existing = getById(id);
    if (existing == null) throw new IllegalArgumentException("bo not found");
    return bookCopyRepository.save(details);
  }

  public void delete(UUID id) {
    bookCopyRepository.deleteById(id);
  }
}

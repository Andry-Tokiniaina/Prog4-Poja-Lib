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
    existing.setBookId(details.getBookId());
    existing.setArrivalId(details.getArrivalId());
    existing.setSaleId(details.getSaleId());
    existing.setAvailable(details.isAvailable());
    existing.setBuy_price(details.getBuy_price());
    existing.setSell_price(details.getSell_price());
    existing.setFormat(details.getFormat());
    return bookCopyRepository.save(existing);
  }

  public void delete(String id) {
    bookCopyRepository.deleteById(id);
  }
}

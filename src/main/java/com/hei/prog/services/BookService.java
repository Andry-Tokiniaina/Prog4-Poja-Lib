package com.hei.prog.services;

import com.hei.prog.entity.Book;
import com.hei.prog.repository.BookRepository;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

  private final BookRepository bookRepository;

  public List<Book> getAll() {
    return bookRepository.findAll();
  }

  public Book getById(UUID id) {
    return bookRepository
        .findById(id)
        .orElseThrow(() -> new RuntimeException("Book not found: " + id));
  }

  public Book create(Book book) {
    return bookRepository.save(book);
  }

  public Book update(UUID id, Book bookDetails) {
    Book existing = getById(id);
    existing.setTitle(bookDetails.getTitle());
    existing.setAuthor(bookDetails.getAuthor());
    existing.setCategory(bookDetails.getCategory());
    return bookRepository.save(existing);
  }

  public void delete(UUID id) {
    bookRepository.deleteById(id);
  }
}

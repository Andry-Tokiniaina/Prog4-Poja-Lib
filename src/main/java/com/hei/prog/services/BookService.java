package com.hei.prog.services;

import com.hei.prog.entity.Book;
import com.hei.prog.repository.BookRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

  private final BookRepository bookRepository;

  public List<Book> getAll() {
    return bookRepository.findAll();
  }

  public Book getById(String id) {
    return bookRepository
        .findById(id)
        .orElseThrow(() -> new RuntimeException("Book not found: " + id));
  }

  public Book create(Book book) {
    return bookRepository.save(book);
  }

  public Book update(String id, Book bookDetails) {
    Book existing = getById(id);
    existing.setTitle(bookDetails.getTitle());
    existing.setAuthor(bookDetails.getAuthor());
    existing.setCategory(bookDetails.getCategory());
    return bookRepository.save(existing);
  }

  public void delete(String id) {
    bookRepository.deleteById(id);
  }
}

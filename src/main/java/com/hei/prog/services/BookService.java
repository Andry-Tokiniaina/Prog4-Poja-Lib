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

  public Book createBook(Book book) {
    if (book.getTitle() == null || book.getTitle().isBlank()) {
      throw new IllegalArgumentException("Le titre du livre est obligatoire");
    }

    bookRepository
        .findByTitleIgnoreCase(book.getTitle())
        .ifPresent(
            b -> {
              throw new IllegalArgumentException("Un livre avec ce titre existe déjà");
            });

    return bookRepository.save(book);
  }
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
    existing.setAuthorId(bookDetails.getAuthorId());
    existing.setCategory(bookDetails.getCategory());
    return bookRepository.save(existing);
  }

  public void delete(String id) {
    bookRepository.deleteById(id);
  }
}

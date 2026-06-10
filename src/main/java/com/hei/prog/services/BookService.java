package com.hei.prog.services;

import com.hei.prog.entity.Book;
import com.hei.prog.repository.BookRepository;
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
}

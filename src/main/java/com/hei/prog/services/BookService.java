package com.hei.prog.services;

import com.hei.prog.entity.Book;
import com.hei.prog.entity.enums.Category;
import com.hei.prog.repository.BookRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

  @Autowired private BookRepository bookRepository;

  public List<Book> getAllBooks() {
    return bookRepository.findAll();
  }

  public Book getBookById(String id) {
    return bookRepository
        .findById(id)
        .orElseThrow(() -> new RuntimeException("Livre non trouvé avec l'id : " + id));
  }

  public List<Book> getBooksByTitle(String title) {
    return bookRepository.findByTitle(title);
  }

  public List<Book> getBooksByTitleContaining(String titlePart) {
    return bookRepository.findByTitleContainingIgnoreCase(titlePart);
  }

  public List<Book> getBooksByAuthor(String author) {
    return bookRepository.findByAuthor(author);
  }

  public List<Book> getBooksByAuthorContaining(String authorPart) {
    return bookRepository.findByAuthorContainingIgnoreCase(authorPart);
  }

  public List<Book> getBooksByCategory(Category category) {
    return bookRepository.findByCategory(category);
  }

  public List<Book> getBooksByCategoryAndTitle(Category category, String titlePart) {
    return bookRepository.findByCategoryAndTitleContainingIgnoreCase(category, titlePart);
  }

  public List<Book> getBooksByAuthorAndCategory(String author, Category category) {
    return bookRepository.findByAuthorAndCategory(author, category);
  }

  public long countBooksByCategory(Category category) {
    return bookRepository.countByCategory(category);
  }

  public Book createBook(Book book) {
    return bookRepository.save(book);
  }

  public Book updateBook(String id, Book bookDetails) {
    Book existingBook = getBookById(id);
    existingBook.setTitle(bookDetails.getTitle());
    existingBook.setAuthor(bookDetails.getAuthor());
    existingBook.setCategory(bookDetails.getCategory());
    existingBook.setBookCopyList(bookDetails.getBookCopyList());
    return bookRepository.save(existingBook);
  }

  public void deleteBook(String id) {
    bookRepository.deleteById(id);
  }
}

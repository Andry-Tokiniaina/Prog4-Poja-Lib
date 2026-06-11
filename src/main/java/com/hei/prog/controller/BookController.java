package com.hei.prog.controller;

import com.hei.prog.entity.Book;
import com.hei.prog.services.BookService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

  private final BookService bookService;

  @GetMapping
  public List<Book> getAll() {
    return bookService.getAll();
  }

  @GetMapping("/{id}")
  public Book getById(@PathVariable String id) {
    return bookService.getById(id);
  }

  @PostMapping
  public Book create(@RequestBody Book book) {
    return bookService.create(book);
  }

  @PutMapping("/{id}")
  public Book update(@PathVariable String id, @RequestBody Book book) {
    return bookService.update(id, book);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable String id) {
    bookService.delete(id);
    return ResponseEntity.noContent().build();
  }
}

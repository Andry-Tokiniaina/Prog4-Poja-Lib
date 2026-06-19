package com.hei.prog.controller;

import com.hei.prog.entity.Book;
import com.hei.prog.services.BookService;
import com.hei.prog.utils.UuidParser;
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
    return bookService.getById(UuidParser.parse(id));
  }

  @PostMapping
  public Book create(@RequestBody Book book) {
    return bookService.create(book);
  }

  @PutMapping("/{id}")
  public Book update(@PathVariable String id, @RequestBody Book book) {
    return bookService.update(UuidParser.parse(id), book);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable String id) {
    bookService.delete(UuidParser.parse(id));
    return ResponseEntity.noContent().build();
  }
}

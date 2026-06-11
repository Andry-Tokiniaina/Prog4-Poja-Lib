package com.hei.prog.controller;

import com.hei.prog.entity.BookCopy;
import com.hei.prog.services.BookCopyService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book-copies")
@RequiredArgsConstructor
public class BookCopyController {

  private final BookCopyService bookCopyService;

  @GetMapping
  public List<BookCopy> getAll() {
    return bookCopyService.getAll();
  }

  @GetMapping("/{id}")
  public BookCopy getById(@PathVariable String id) {
    return bookCopyService.getById(id);
  }

  @GetMapping("/book/{bookId}")
  public List<BookCopy> getByBookId(@PathVariable String bookId) {
    return bookCopyService.getByBookId(bookId);
  }

  @PostMapping
  public BookCopy create(@RequestBody BookCopy bookCopy) {
    return bookCopyService.create(bookCopy);
  }

  @PutMapping("/{id}")
  public BookCopy update(@PathVariable String id, @RequestBody BookCopy bookCopy) {
    return bookCopyService.update(id, bookCopy);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable String id) {
    bookCopyService.delete(id);
    return ResponseEntity.noContent().build();
  }
}

package com.hei.prog.controller;

import com.hei.prog.entity.Book;
import com.hei.prog.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

  private final BookService bookService;

  // POST /books
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Book createBook(@RequestBody Book book) {
    return bookService.createBook(book);
  }

   // DELETE /books/{id}
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void deleteBook(@PathVariable String id) {
    bookService.deleteBook(id);
  }
}

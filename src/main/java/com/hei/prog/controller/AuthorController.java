package com.hei.prog.controller;

import com.hei.prog.entity.Author;
import com.hei.prog.services.AuthorService;
import com.hei.prog.utils.UuidParser;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {

  private final AuthorService authorService;

  @GetMapping
  public List<Author> getAll() {
    return authorService.getAll();
  }

  @GetMapping("/{id}")
  public Author getById(@PathVariable String id) {
    return authorService.getById(UuidParser.parse(id));
  }

  @PostMapping
  public ResponseEntity<Author> create(@RequestBody Author author) {
    return ResponseEntity.status(HttpStatus.CREATED).body(authorService.create(author));
  }

  @PutMapping("/{id}")
  public Author update(@PathVariable String id, @RequestBody Author author) {
    return authorService.update(UuidParser.parse(id), author);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable String id) {
    authorService.delete(UuidParser.parse(id));
    return ResponseEntity.noContent().build();
  }
}

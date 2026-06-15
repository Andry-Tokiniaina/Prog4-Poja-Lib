package com.hei.prog.services;

import com.hei.prog.entity.Author;
import com.hei.prog.repository.AuthorRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorService {
  private final AuthorRepository authorRepository;

  public Author saveAuthor(Author author) {
    return authorRepository.save(author);
  }

  public List<Author> getAllAuthors() {
    return authorRepository.findAll();
  }

  public Optional<Author> getAuthorById(String id) {
    return authorRepository.findById(id);
  }

  public void deleteAuthorById(String id) {
    authorRepository.deleteById(id);
  }
  // à compléter le moment venu
}

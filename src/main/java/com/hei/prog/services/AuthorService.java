package com.hei.prog.services;

import com.hei.prog.endpoint.exception.NotFoundException;
import com.hei.prog.entity.Author;
import com.hei.prog.repository.AuthorRepository;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorService {

  private final AuthorRepository authorRepository;

  public List<Author> getAll() {
    return authorRepository.findAll();
  }

  public Author getById(UUID id) {
    return authorRepository.findById(id).orElseThrow(() -> new NotFoundException("Author", id));
  }

  public Author create(Author author) {
    return authorRepository.save(author);
  }

  public Author update(UUID id, Author authorDetails) {
    Author existing = getById(id);
    existing.setFirstname(authorDetails.getFirstname());
    existing.setLastname(authorDetails.getLastname());
    return authorRepository.save(existing);
  }

  public void delete(UUID id) {
    authorRepository.deleteById(id);
  }
}

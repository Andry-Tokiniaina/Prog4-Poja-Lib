package com.hei.prog.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Author {
  @Id private String id;
  private String firstname;
  private String lastname;

  @OneToMany(mappedBy = "author")
  private List<Book> books;
}

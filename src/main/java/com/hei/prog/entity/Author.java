package com.hei.prog.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Author {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  private String firstname;
  private String lastname;

  @JsonIgnore
  @OneToMany(mappedBy = "author")
  private List<Book> books;
}

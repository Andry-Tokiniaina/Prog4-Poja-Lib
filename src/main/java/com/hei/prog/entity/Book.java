package com.hei.prog.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hei.prog.entity.enums.Category;
import jakarta.persistence.*;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Book {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  private String title;

  @ManyToOne private Author author;

  @JsonIgnore
  @OneToMany(mappedBy = "book")
  private List<BookCopy> bookCopyList;

  @Enumerated(EnumType.STRING)
  private Category category;
}

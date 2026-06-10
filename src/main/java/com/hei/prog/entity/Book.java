package com.hei.prog.entity;

import com.hei.prog.entity.enums.BookFormat;
import com.hei.prog.entity.enums.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  private String title;

  @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author author;


  @Enumerated(EnumType.STRING)
  private Category category;

  @Enumerated(EnumType.STRING)
    private BookFormat format;

  private String isbn;
  private Integer publicationYear;
}

package com.hei.prog.entity;

import com.hei.prog.entity.enums.Category;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book {
  @Id private String id;
  private String title;

  @ManyToOne private Author author;

  @OneToMany(mappedBy = "book_copy")
  private List<BookCopy> bookCopyList;

  private Category category;
}

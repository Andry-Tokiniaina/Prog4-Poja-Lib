package com.hei.prog.entity;

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
  private String authorId;

  @Enumerated(EnumType.STRING)
  private Category category;
}

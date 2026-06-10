package com.hei.prog.entity;

import com.hei.prog.entity.enums.Category;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
  @Id private String id;
  private String title;
  private String authorId;

  @Enumerated(EnumType.STRING)
  private Category category;
}

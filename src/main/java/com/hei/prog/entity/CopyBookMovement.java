package com.hei.prog.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CopyBookMovement {
  @Id private String id;
  private BookCopy bookCopy;
  private int number;
  @ManyToOne private Movement movement;
}

package com.hei.prog.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
  @ManyToOne private BookCopy bookCopy;
  private int number;

  @ManyToOne
  @JoinColumn(name = "movement_id")
  private Movement movement;
}

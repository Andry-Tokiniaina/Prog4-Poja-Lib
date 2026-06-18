package com.hei.prog.entity;

import jakarta.persistence.*;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CopyBookMovement {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @ManyToOne private BookCopy bookCopy;
  private int number;

  @ManyToOne
  @JoinColumn(name = "movement_id")
  private Movement movement;
}

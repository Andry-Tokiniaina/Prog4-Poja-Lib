package com.hei.prog.entity;

import jakarta.persistence.*;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Arrival extends Movement {
  @OneToMany(mappedBy = "movement")
  private List<CopyBookMovement> copyBooks;
}

package com.hei.prog.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
public class Sale extends Movement {
  @OneToMany(mappedBy = "movement")
  private List<CopyBookMovement> copyBooks;

  @ManyToOne private Customer customer;
}

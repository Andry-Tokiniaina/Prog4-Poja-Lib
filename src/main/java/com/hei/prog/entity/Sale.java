package com.hei.prog.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Sale {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  private List<BookCopy> book;
  private Customer customer;
  private DateTime date;
}

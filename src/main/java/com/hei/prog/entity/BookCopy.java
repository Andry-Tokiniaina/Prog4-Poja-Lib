package com.hei.prog.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BookCopy {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  private Book bookcopy;
  private boolean available;
  private double buy_price;
  private double sell_price;
}

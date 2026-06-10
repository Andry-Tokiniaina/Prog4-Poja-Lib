package com.hei.prog.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookCopy {
  @Id
  private String id;

  private String bookId;
  private String arrivalId;
  private String saleId;
  private boolean available;
  private double buy_price;
  private double sell_price;
}
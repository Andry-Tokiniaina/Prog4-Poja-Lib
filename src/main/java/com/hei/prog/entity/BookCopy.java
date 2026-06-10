package com.hei.prog.entity;

import com.hei.prog.entity.enums.BookFormat;
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
public class BookCopy {
  @Id private String id;
  private String bookId;
  private String arrivalId;
  private String saleId;
  private boolean available;
  private double buy_price;
  private double sell_price;
  @Enumerated(EnumType.STRING)
  private BookFormat format;
}

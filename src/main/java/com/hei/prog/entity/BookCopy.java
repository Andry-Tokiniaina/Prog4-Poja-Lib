package com.hei.prog.entity;

import com.hei.prog.entity.enums.BookFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BookCopy {
  @Id private String id;
  @ManyToOne private Book bookcopy;
  private BookFormat format;
  private double buy_price;
  private double sell_price;
}

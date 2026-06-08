package com.hei.prog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookCopy {
  private String id;
  private Book bookcopy;
  private boolean available;
  private double buy_price;
  private double sell_price;
}

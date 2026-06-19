package com.hei.prog.entity;

import com.hei.prog.entity.enums.BookFormat;
import jakarta.persistence.*;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BookCopy {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @ManyToOne private Book book;

  @Enumerated(EnumType.STRING)
  private BookFormat format;

  private double buy_price;
  private double sell_price;
}

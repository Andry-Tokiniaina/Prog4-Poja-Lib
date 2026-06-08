package com.hei.prog.entity;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Component
@Data
public class Sale {
  private String id;
  private List<BookCopy> book;
  private Customer customer;
  private DateTime date;
}

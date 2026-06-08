package com.hei.prog.entity;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author {
  private String id;
  private String firstname;
  private String lastname;
  private List<Book> book;
}

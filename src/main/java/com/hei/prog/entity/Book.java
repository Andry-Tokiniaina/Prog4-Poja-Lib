package com.hei.prog.entity;

import com.hei.prog.entity.enums.Category;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
  private String id;
  private String title;
  private Author author;
  private List<BookCopy> bookCopyList;
  private Category category;
}

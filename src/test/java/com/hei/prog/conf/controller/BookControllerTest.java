package com.hei.prog.conf.controller;

import static org.mockito.Mockito.when;

import com.hei.prog.entity.Author;
import com.hei.prog.entity.Book;
import com.hei.prog.entity.enums.Category;
import com.hei.prog.services.BookCopyService;
import com.hei.prog.services.BookService;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

public class BookControllerTest {
  private Book LePetitPrince;
  private Book HarryPotter;
  @Autowired private MockMvc mockMvc;
  @MockBean private BookService bookService;
  @MockBean private BookCopyService bookCopyService;

  @BeforeEach
  void setUp() {
    Author saintExupery =
        new Author(UUID.randomUUID(), "Antoine", "De Saint Exupery", new ArrayList<>());

    LePetitPrince =
        Book.builder()
            .id(UUID.randomUUID())
            .title("Le Petit Prince")
            .author(saintExupery)
            .bookCopyList(new ArrayList<>())
            .category(Category.HISTORY)
            .build();

    Author Jk = new Author(UUID.randomUUID(), "JK", "Rowling", new ArrayList<>());

    HarryPotter =
        Book.builder()
            .id(UUID.randomUUID())
            .title("Harry Potter")
            .author(Jk)
            .bookCopyList(new ArrayList<>())
            .category(Category.FICTION)
            .build();
  }

  @Test
  void getBook_shouldReturn200() {
    when(bookService.getAll()).thenReturn(List.of(HarryPotter));
    when(bookService.getAll()).thenReturn(List.of(LePetitPrince));
  }
}

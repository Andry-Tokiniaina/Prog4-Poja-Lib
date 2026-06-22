package com.hei.prog.conf.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hei.prog.controller.BookController;
import com.hei.prog.entity.Author;
import com.hei.prog.entity.Book;
import com.hei.prog.entity.enums.Category;
import com.hei.prog.services.BookService;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(BookController.class)
class BookControllerTest {

  @Autowired private MockMvc mockMvc;

  @Autowired private ObjectMapper objectMapper;

  @MockBean private BookService bookService;

  private Book lePetitPrince;
  private Book harryPotter;

  @BeforeEach
  void setUp() {

    Author saintExupery =
        new Author(UUID.randomUUID(), "Antoine", "De Saint Exupery", new ArrayList<>());

    Author jkRowling = new Author(UUID.randomUUID(), "JK", "Rowling", new ArrayList<>());

    lePetitPrince =
        Book.builder()
            .id(UUID.randomUUID())
            .title("Le Petit Prince")
            .author(saintExupery)
            .category(Category.HISTORY)
            .bookCopyList(new ArrayList<>())
            .build();

    harryPotter =
        Book.builder()
            .id(UUID.randomUUID())
            .title("Harry Potter")
            .author(jkRowling)
            .category(Category.FICTION)
            .bookCopyList(new ArrayList<>())
            .build();
  }

  @Test
  void getAllBooks_shouldReturn200AndBooks() throws Exception {

    when(bookService.getAll()).thenReturn(List.of(lePetitPrince, harryPotter));

    mockMvc
        .perform(get("/books"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.length()").value(2))
        .andExpect(jsonPath("$[0].title").value("Le Petit Prince"))
        .andExpect(jsonPath("$[1].title").value("Harry Potter"));
  }

  @Test
  void getBookById_shouldReturnBook() throws Exception {

    when(bookService.getById(harryPotter.getId())).thenReturn(harryPotter);

    mockMvc
        .perform(get("/books/" + harryPotter.getId()))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(harryPotter.getId().toString()))
        .andExpect(jsonPath("$.title").value("Harry Potter"))
        .andExpect(jsonPath("$.category").value("FICTION"))
        .andExpect(jsonPath("$.author.firstname").value("JK"))
        .andExpect(jsonPath("$.author.lastname").value("Rowling"));
  }

  @Test
  void createBook_shouldReturn201() throws Exception {

    when(bookService.create(any(Book.class))).thenReturn(harryPotter);

    mockMvc
        .perform(
            post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(harryPotter)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.title").value("Harry Potter"))
        .andExpect(jsonPath("$.category").value("FICTION"));
  }

  @Test
  void updateBook_shouldReturnUpdatedBook() throws Exception {

    Book updatedBook =
        Book.builder()
            .id(harryPotter.getId())
            .title("Harry Potter Updated")
            .author(harryPotter.getAuthor())
            .category(Category.FICTION)
            .bookCopyList(new ArrayList<>())
            .build();

    when(bookService.update(any(UUID.class), any(Book.class))).thenReturn(updatedBook);

    mockMvc
        .perform(
            put("/books/" + harryPotter.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedBook)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.title").value("Harry Potter Updated"));
  }

  @Test
  void deleteBook_shouldReturn204() throws Exception {

    doNothing().when(bookService).delete(harryPotter.getId());

    mockMvc.perform(delete("/books/" + harryPotter.getId())).andExpect(status().isNoContent());
  }
}

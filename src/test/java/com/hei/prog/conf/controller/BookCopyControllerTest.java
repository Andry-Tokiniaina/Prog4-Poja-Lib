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
import com.hei.prog.controller.BookCopyController;
import com.hei.prog.endpoint.exception.NotFoundException;
import com.hei.prog.entity.Author;
import com.hei.prog.entity.Book;
import com.hei.prog.entity.BookCopy;
import com.hei.prog.entity.enums.BookFormat;
import com.hei.prog.entity.enums.Category;
import com.hei.prog.services.BookCopyService;
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

@WebMvcTest(BookCopyController.class)
class BookCopyControllerTest {

  @Autowired private MockMvc mockMvc;

  @Autowired private ObjectMapper objectMapper;

  @MockBean private BookCopyService bookCopyService;

  private Book harryPotter;
  private BookCopy brocheCopy;
  private BookCopy relieCopy;

  @BeforeEach
  void setUp() {

    Author jkRowling = new Author(UUID.randomUUID(), "JK", "Rowling", new ArrayList<>());

    harryPotter =
        Book.builder()
            .id(UUID.randomUUID())
            .title("Harry Potter")
            .author(jkRowling)
            .category(Category.FICTION)
            .bookCopyList(new ArrayList<>())
            .build();

    brocheCopy = new BookCopy(UUID.randomUUID(), harryPotter, BookFormat.BROCHE, 8.99, 14.99);
    relieCopy = new BookCopy(UUID.randomUUID(), harryPotter, BookFormat.RELIE, 15.00, 24.99);
  }

  @Test
  void getBookCopies_withExistingUUID_shouldReturn200() throws Exception {

    when(bookCopyService.getAll()).thenReturn(List.of(brocheCopy, relieCopy));

    mockMvc
        .perform(get("/book-copies"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.length()").value(2))
        .andExpect(jsonPath("$[0].format").value("BROCHE"))
        .andExpect(jsonPath("$[1].format").value("RELIE"));
  }

  @Test
  void getBookCopyById_withExistingUUID_shouldReturn200() throws Exception {

    when(bookCopyService.getById(brocheCopy.getId())).thenReturn(brocheCopy);

    mockMvc
        .perform(get("/book-copies/" + brocheCopy.getId()))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(brocheCopy.getId().toString()))
        .andExpect(jsonPath("$.format").value("BROCHE"))
        .andExpect(jsonPath("$.sell_price").value(14.99))
        .andExpect(jsonPath("$.buy_price").value(8.99));
  }

  @Test
  void getBookCopyById_with_invalidUUID_shouldThrow400() throws Exception {

    mockMvc.perform(get("/book-copies/not-a-valid-uuid")).andExpect(status().isBadRequest());
  }

  @Test
  void getBookCopyById_with_nonExistingBookCopy_shouldThrow404() throws Exception {

    UUID unknownId = UUID.randomUUID();
    when(bookCopyService.getById(unknownId))
        .thenThrow(new NotFoundException("BookCopy", unknownId));

    mockMvc.perform(get("/book-copies/" + unknownId)).andExpect(status().isNotFound());
  }

  @Test
  void createBookCopy_shouldReturn200() throws Exception {

    when(bookCopyService.create(any(BookCopy.class))).thenReturn(brocheCopy);

    mockMvc
        .perform(
            post("/book-copies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(brocheCopy)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.format").value("BROCHE"))
        .andExpect(jsonPath("$.sell_price").value(14.99));
  }

  @Test
  void updateBookCopy_shouldReturnUpdatedBookCopy() throws Exception {

    BookCopy updatedCopy =
        new BookCopy(brocheCopy.getId(), harryPotter, BookFormat.POCHE, 5.00, 9.99);

    when(bookCopyService.update(any(UUID.class), any(BookCopy.class))).thenReturn(updatedCopy);

    mockMvc
        .perform(
            put("/book-copies/" + brocheCopy.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedCopy)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.format").value("POCHE"))
        .andExpect(jsonPath("$.sell_price").value(9.99));
  }

  @Test
  void deleteBookCopy_shouldReturn204() throws Exception {

    doNothing().when(bookCopyService).delete(brocheCopy.getId());

    mockMvc.perform(delete("/book-copies/" + brocheCopy.getId())).andExpect(status().isNoContent());
  }
}

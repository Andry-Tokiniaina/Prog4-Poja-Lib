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
import com.hei.prog.controller.AuthorController;
import com.hei.prog.endpoint.exception.NotFoundException;
import com.hei.prog.entity.Author;
import com.hei.prog.services.AuthorService;
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

@WebMvcTest(AuthorController.class)
class AuthorControllerTest {

  @Autowired private MockMvc mockMvc;

  @Autowired private ObjectMapper objectMapper;

  @MockBean private AuthorService authorService;

  private Author tolkien;
  private Author hugo;

  @BeforeEach
  void setUp() {
    tolkien = new Author(UUID.randomUUID(), "J.R.R", "Tolkien", new ArrayList<>());
    hugo = new Author(UUID.randomUUID(), "Victor", "Hugo", new ArrayList<>());
  }

  @Test
  void getById_withExistingId_shouldReturn200() throws Exception {
    when(authorService.getById(tolkien.getId())).thenReturn(tolkien);

    mockMvc
        .perform(get("/authors/" + tolkien.getId()))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(tolkien.getId().toString()))
        .andExpect(jsonPath("$.firstname").value("J.R.R"))
        .andExpect(jsonPath("$.lastname").value("Tolkien"));
  }

  @Test
  void getById_withNonExistingId_shouldThrow404() throws Exception {
    UUID unknownId = UUID.randomUUID();
    when(authorService.getById(unknownId)).thenThrow(new NotFoundException("Author", unknownId));

    mockMvc.perform(get("/authors/" + unknownId)).andExpect(status().isNotFound());
  }

  @Test
  void getById_withInvalidId_shouldThrow400() throws Exception {
    mockMvc.perform(get("/authors/not-a-valid-uuid")).andExpect(status().isBadRequest());
  }

  @Test
  void getAll_shouldReturn200AndAuthors() throws Exception {
    when(authorService.getAll()).thenReturn(List.of(tolkien, hugo));

    mockMvc
        .perform(get("/authors"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.length()").value(2))
        .andExpect(jsonPath("$[0].lastname").value("Tolkien"))
        .andExpect(jsonPath("$[1].lastname").value("Hugo"));
  }

  @Test
  void create_shouldReturn201() throws Exception {
    when(authorService.create(any(Author.class))).thenReturn(tolkien);

    mockMvc
        .perform(
            post("/authors")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tolkien)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.firstname").value("J.R.R"))
        .andExpect(jsonPath("$.lastname").value("Tolkien"));
  }

  @Test
  void update_shouldReturnUpdatedAuthor() throws Exception {
    Author updated = new Author(tolkien.getId(), "J.R.R", "Tolkien Updated", new ArrayList<>());
    when(authorService.update(any(UUID.class), any(Author.class))).thenReturn(updated);

    mockMvc
        .perform(
            put("/authors/" + tolkien.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updated)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.lastname").value("Tolkien Updated"));
  }

  @Test
  void delete_shouldReturn204() throws Exception {
    doNothing().when(authorService).delete(tolkien.getId());

    mockMvc.perform(delete("/authors/" + tolkien.getId())).andExpect(status().isNoContent());
  }
}

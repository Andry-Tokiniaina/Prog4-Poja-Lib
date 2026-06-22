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
import com.hei.prog.controller.CustomerController;
import com.hei.prog.endpoint.exception.NotFoundException;
import com.hei.prog.entity.Customer;
import com.hei.prog.services.CustomerService;
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

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

  @Autowired private MockMvc mockMvc;

  @Autowired private ObjectMapper objectMapper;

  @MockBean private CustomerService customerService;

  private Customer alice;
  private Customer bob;

  @BeforeEach
  void setUp() {
    alice = new Customer(UUID.randomUUID(), "Alice", "Dupont", new ArrayList<>());
    bob = new Customer(UUID.randomUUID(), "Bob", "Martin", new ArrayList<>());
  }

  @Test
  void getById_when_customerExists_shouldReturn200() throws Exception {
    when(customerService.getById(alice.getId())).thenReturn(alice);

    mockMvc
        .perform(get("/customers/" + alice.getId()))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(alice.getId().toString()))
        .andExpect(jsonPath("$.firstname").value("Alice"))
        .andExpect(jsonPath("$.lastname").value("Dupont"));
  }

  @Test
  void getById_when_customerDoesNotExist_shouldThrow404() throws Exception {
    UUID unknownId = UUID.randomUUID();
    when(customerService.getById(unknownId))
        .thenThrow(new NotFoundException("Customer", unknownId));

    mockMvc.perform(get("/customers/" + unknownId)).andExpect(status().isNotFound());
  }

  @Test
  void getById_withInvalidId_shouldThrow400() throws Exception {
    mockMvc.perform(get("/customers/not-a-valid-uuid")).andExpect(status().isBadRequest());
  }

  @Test
  void getAll_shouldReturn200AndCustomers() throws Exception {
    when(customerService.getAll()).thenReturn(List.of(alice, bob));

    mockMvc
        .perform(get("/customers"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.length()").value(2))
        .andExpect(jsonPath("$[0].firstname").value("Alice"))
        .andExpect(jsonPath("$[1].firstname").value("Bob"));
  }

  @Test
  void create_shouldReturn201() throws Exception {
    when(customerService.create(any(Customer.class))).thenReturn(alice);

    mockMvc
        .perform(
            post("/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(alice)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.firstname").value("Alice"))
        .andExpect(jsonPath("$.lastname").value("Dupont"));
  }

  @Test
  void update_shouldReturnUpdatedCustomer() throws Exception {
    Customer updated = new Customer(alice.getId(), "Alice", "Dupont Updated", new ArrayList<>());
    when(customerService.update(any(UUID.class), any(Customer.class))).thenReturn(updated);

    mockMvc
        .perform(
            put("/customers/" + alice.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updated)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.lastname").value("Dupont Updated"));
  }

  @Test
  void delete_shouldReturn204() throws Exception {
    doNothing().when(customerService).delete(alice.getId());

    mockMvc.perform(delete("/customers/" + alice.getId())).andExpect(status().isNoContent());
  }
}

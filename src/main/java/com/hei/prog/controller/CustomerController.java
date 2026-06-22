package com.hei.prog.controller;

import com.hei.prog.entity.Customer;
import com.hei.prog.services.CustomerService;
import com.hei.prog.utils.UuidParser;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

  private final CustomerService customerService;

  @GetMapping
  public List<Customer> getAll() {
    return customerService.getAll();
  }

  @GetMapping("/{id}")
  public Customer getById(@PathVariable String id) {
    return customerService.getById(UuidParser.parse(id));
  }

  @PostMapping
  public ResponseEntity<Customer> create(@RequestBody Customer customer) {
    return ResponseEntity.status(HttpStatus.CREATED).body(customerService.create(customer));
  }

  @PutMapping("/{id}")
  public Customer update(@PathVariable String id, @RequestBody Customer customer) {
    return customerService.update(UuidParser.parse(id), customer);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable String id) {
    customerService.delete(UuidParser.parse(id));
    return ResponseEntity.noContent().build();
  }
}

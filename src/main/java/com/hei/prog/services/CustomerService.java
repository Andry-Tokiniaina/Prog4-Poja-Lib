package com.hei.prog.services;

import com.hei.prog.endpoint.exception.NotFoundException;
import com.hei.prog.entity.Customer;
import com.hei.prog.repository.CustomerRepository;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

  private final CustomerRepository customerRepository;

  public List<Customer> getAll() {
    return customerRepository.findAll();
  }

  public Customer getById(UUID id) {
    return customerRepository.findById(id).orElseThrow(() -> new NotFoundException("Customer", id));
  }

  public Customer create(Customer customer) {
    return customerRepository.save(customer);
  }

  public Customer update(UUID id, Customer customerDetails) {
    Customer existing = getById(id);
    existing.setFirstname(customerDetails.getFirstname());
    existing.setLastname(customerDetails.getLastname());
    return customerRepository.save(existing);
  }

  public void delete(UUID id) {
    customerRepository.deleteById(id);
  }
}

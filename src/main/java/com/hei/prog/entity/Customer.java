package com.hei.prog.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Customer {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  private String firstname;
  private String lastname;

  @OneToMany(mappedBy = "customer")
  @JsonIgnore
  private List<Sale> sales;
}

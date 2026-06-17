package com.hei.prog.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Customer {
  @Id private String id;
  private String firstname;
  private String lastname;

  @OneToMany(mappedBy = "sale")
  private List<Sale> sales;
}

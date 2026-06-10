package com.hei.prog.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Component
@Data
@Entity
public class Sale {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;
  private List<BookCopy> book;
  private Customer customer;
  private DateTime date;
}

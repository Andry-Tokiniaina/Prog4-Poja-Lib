package com.hei.prog.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
@Entity
public class Arrival {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  private List<BookCopy> books;
  private DateTime dateTime;
}

package com.hei.prog.entity;

import jakarta.persistence.*;
import java.util.UUID;
import org.joda.time.DateTime;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Movement {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  private DateTime date;
}

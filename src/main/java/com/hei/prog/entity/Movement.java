package com.hei.prog.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import org.joda.time.DateTime;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Movement {
  @Id private String id;
  private DateTime date;
}

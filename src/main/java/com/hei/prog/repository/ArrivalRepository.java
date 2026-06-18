package com.hei.prog.repository;

import com.hei.prog.entity.Arrival;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArrivalRepository extends JpaRepository<Arrival, String> {
  default void createArrival(Arrival arrival) {
    save(arrival);
  }
}

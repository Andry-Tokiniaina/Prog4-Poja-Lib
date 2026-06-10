package com.hei.prog.repository;

import com.hei.prog.entity.Arrival;
import java.time.Instant;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ArrivalRepository extends JpaRepository<Arrival, String> {
  @Query("SELECT a FROM Arrival a WHERE a.receivedAt <= :t")
  List<Arrival> getReceivedAt(@Param("t") Instant t);

  default void createArrival(Arrival arrival) {
    save(arrival);
  }
}

package com.hei.prog.repository;

import com.hei.prog.entity.Arrival;
import java.util.List;
import java.util.UUID;
import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ArrivalRepository extends JpaRepository<Arrival, UUID> {
  default void createArrival(Arrival arrival) {
    save(arrival);
  }

  @Query("SELECT a FROM Arrival a WHERE a.date <= :t")
  List<Arrival> getArrivalsAt(@Param("t") DateTime t);
}

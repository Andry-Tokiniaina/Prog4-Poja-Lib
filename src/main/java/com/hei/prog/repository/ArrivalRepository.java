package com.hei.prog.repository;

import com.hei.prog.entity.Arrival;
import java.time.Instant;
import java.util.List;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@NoArgsConstructor
public class ArrivalRepository {
  public List<Arrival> getReceivedAt(Instant t) {
    throw new RuntimeException("Not implemented yet");
  }

  public void createArrival(Arrival arrival) {
    throw new RuntimeException("Not implemented yet");
  }
}

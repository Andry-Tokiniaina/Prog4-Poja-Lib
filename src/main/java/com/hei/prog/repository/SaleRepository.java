package com.hei.prog.repository;

import com.hei.prog.entity.Sale;
import java.time.Instant;
import java.util.List;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@NoArgsConstructor
public class SaleRepository {
  public List<Sale> getSalesAt(Instant t) {
    throw new RuntimeException("Not implemented yet");
  }

  public void createSale(Sale sale) {
    throw new RuntimeException("Not implemented yet");
  }
}

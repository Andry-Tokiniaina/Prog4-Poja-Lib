package com.hei.prog.repository;

import com.hei.prog.entity.Sale;
import java.time.Instant;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<Sale, String> {
  @Query("SELECT s FROM Sale s WHERE s.soldAt <= :t")
  List<Sale> getSalesAt(@Param("t") Instant t);

  default void createSale(Sale sale) {
    save(sale);
  }
}

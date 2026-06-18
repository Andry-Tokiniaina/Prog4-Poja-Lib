package com.hei.prog.repository;

import com.hei.prog.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<Sale, String> {

  default void createSale(Sale sale) {
    save(sale);
  }
}

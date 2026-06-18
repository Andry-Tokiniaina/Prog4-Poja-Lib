package com.hei.prog.repository;

import com.hei.prog.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SaleRepository extends JpaRepository<Sale, UUID> {

  default void createSale(Sale sale) {
    save(sale);
  }
}

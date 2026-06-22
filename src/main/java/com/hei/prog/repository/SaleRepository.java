package com.hei.prog.repository;

import com.hei.prog.entity.Sale;
import java.util.List;
import java.util.UUID;
import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<Sale, UUID> {
  @Query("SELECT s FROM Sale s WHERE s.date <= :t")
  List<Sale> getSalesAt(@Param("t") DateTime t);
}

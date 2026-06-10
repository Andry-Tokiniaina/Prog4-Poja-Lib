package com.hei.prog.repository;

import com.hei.prog.entity.Sale;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<Sale, String> {
}
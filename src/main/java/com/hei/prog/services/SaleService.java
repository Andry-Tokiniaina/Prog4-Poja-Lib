package com.hei.prog.services;

import com.hei.prog.entity.Sale;
import com.hei.prog.repository.SaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SaleService {
    private final SaleRepository saleRepository;

    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }

    public Optional<Sale> getSaleById(String id) {
        return saleRepository.findById(id);
    }

    public void deleteSaleById(String id) {
        saleRepository.deleteById(id);
    }

    public List<Sale> getSalesAt(Instant t) {
        return saleRepository.getSalesAt(t);
    }

    // à completer le moment venu
}

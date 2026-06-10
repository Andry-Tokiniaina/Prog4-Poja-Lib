package com.hei.prog.services;

import com.hei.prog.entity.Arrival;
import com.hei.prog.entity.BookCopy;
import com.hei.prog.entity.Sale;
import com.hei.prog.repository.ArrivalRepository;
import com.hei.prog.repository.SaleRepository;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockService {
  private final ArrivalRepository arrivalRepository;
  private final SaleRepository saleRepository;

  public List<BookCopy> getStockAt(Instant t) {
    List<BookCopy> bookCopies = new ArrayList<>(getBookReceivedAt(t));

    for (Sale s : saleRepository.getSalesAt(t)) {
      // en attente de bookrepository
    }

    return bookCopies;
  }

  public List<BookCopy> getBookSalesAt(Instant t) {
    List<BookCopy> bookCopies = new ArrayList<>();
    for (Sale s : saleRepository.getSalesAt(t)) {
      // en attente de bookrepository
    }
    return bookCopies;
  }

  public List<BookCopy> getBookReceivedAt(Instant t) {
    List<BookCopy> bookCopies = new ArrayList<>();
    for (Arrival a : arrivalRepository.getReceivedAt(t)) {
      // en attente de bookrepository
    }
    return bookCopies;
  }

  public boolean getBookByName(String book_name) {
    if (book_name == null) {
      throw new IllegalArgumentException("need the name of the book");
    }
    return true;
    // en attente de bookrepository
  }

  public void createArrival(Arrival arrival) {
    arrivalRepository.createArrival(arrival);
  }

  public void createSell(Sale sale) {
    saleRepository.createSale(sale);
  }
}

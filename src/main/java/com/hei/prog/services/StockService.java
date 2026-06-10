package com.hei.prog.services;

import com.hei.prog.entity.Arrival;
import com.hei.prog.entity.BookCopy;
import com.hei.prog.entity.Sale;
import com.hei.prog.repository.ArrivalRepository;
import com.hei.prog.repository.SaleRepository;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class StockService {
  private ArrivalRepository arrivalRepository;
  private SaleRepository saleRepository;

  public List<BookCopy> getStockAt(Instant t) {

    List<BookCopy> bookCopies = new ArrayList<>();

    for (Arrival a : arrivalRepository.getReceivedAt(t)) {
      bookCopies.addAll(a.getBooks());
    }

    for (Sale s : saleRepository.getSalesAt(t)) {
      for (BookCopy bc : bookCopies) {
        for (BookCopy sbc : s.getBook()) {
          if (Objects.equals(bc.getId(), sbc.getId())) {
            bookCopies.remove(bc);
          }
        }
      }
    }

    return bookCopies;
  }

  public List<BookCopy> getBookSalesAt(Instant t) {
    List<BookCopy> bookCopies = new ArrayList<>();

    for (Sale s : saleRepository.getSalesAt(t)) {
      bookCopies.addAll(s.getBook());
    }

    return bookCopies;
  }

  public List<BookCopy> getBookReceivedAt(Instant t) {

    List<BookCopy> bookCopies = new ArrayList<>();

    for (Arrival a : arrivalRepository.getReceivedAt(t)) {
      bookCopies.addAll(a.getBooks());
    }

    return bookCopies;
  }

  public boolean getBookByName(String book_name) {
    if (book_name == null) {
      throw new IllegalArgumentException("need the name of the book");
    }
    return this.getStockAt(Instant.now()).stream()
        .map(b -> b.getBookcopy().getTitle())
        .anyMatch(title -> title.equals(book_name));
  }

  public void createArrival(Arrival arrival) {
    arrivalRepository.createArrival(arrival);
  }

  public void createSell(Sale sale) {
    saleRepository.createSale(sale);
  }
}

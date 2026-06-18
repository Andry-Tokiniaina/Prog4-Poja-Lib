package com.hei.prog.controller;

import com.hei.prog.entity.Arrival;
import com.hei.prog.entity.BookCopy;
import com.hei.prog.entity.Sale;
import com.hei.prog.services.StockService;
import java.time.Instant;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stock")
@RequiredArgsConstructor
public class StockController {

  private final StockService stockService;
/*
  // GET /stock?t=2024-01-01T00:00:00Z
  @GetMapping
  public List<BookCopy> getStockAt(@RequestParam Instant t) {
    return stockService.getStockAt(t);
  }

  // GET /stock/arrivals?t=2024-01-01T00:00:00Z
  @GetMapping("/arrivals")
  public List<BookCopy> getBookReceivedAt(@RequestParam Instant t) {
    return stockService.getBookReceivedAt(t);
  }

  // GET /stock/sales?t=2024-01-01T00:00:00Z
  @GetMapping("/sales")
  public List<BookCopy> getBookSalesAt(@RequestParam Instant t) {
    return stockService.getBookSalesAt(t);
  }
**/
  // GET /stock/search?book_name=Harry+Potter
  @GetMapping("/search")
  public boolean getBookByName(@RequestParam String book_name) {
    return stockService.getBookByName(book_name);
  }

  // POST /stock/arrivals
  @PostMapping("/arrivals")
  @ResponseStatus(HttpStatus.CREATED)
  public void createArrival(@RequestBody Arrival arrival) {
    stockService.createArrival(arrival);
  }

  // POST /stock/sales
  @PostMapping("/sales")
  @ResponseStatus(HttpStatus.CREATED)
  public void createSell(@RequestBody Sale sale) {
    stockService.createSell(sale);
  }
}

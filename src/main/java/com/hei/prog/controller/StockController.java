package com.hei.prog.controller;

import com.hei.prog.entity.Arrival;
import com.hei.prog.entity.BookCopy;
import com.hei.prog.entity.Sale;
import com.hei.prog.services.StockService;
import java.util.Map;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stock")
@RequiredArgsConstructor
public class StockController {

  private final StockService stockService;

  /**
   * GET /api/stock?t=2024-01-15T10:30:00
   * Retourne le stock (UUID -> quantité) à un instant t.
   * Si t est absent, utilise l'instant présent.
   */
  @GetMapping
  public ResponseEntity<Map<UUID, Integer>> getStockAt(
          @RequestParam(required = false)
          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
          DateTime t) {
    DateTime time = (t != null) ? t : DateTime.now();
    return ResponseEntity.ok(stockService.getStockAt(time));
  }

  /**
   * GET /api/stock/book-copies?t=2024-01-15T10:30:00
   * Retourne le stock par BookCopy (objet complet -> quantité) à un instant t.
   */
  @GetMapping("/book-copies")
  public ResponseEntity<Map<BookCopy, Integer>> getBookCopyStockAt(
          @RequestParam(required = false)
          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
          DateTime t) {
    DateTime time = (t != null) ? t : DateTime.now();
    return ResponseEntity.ok(stockService.getBookCopyStockAt(time));
  }

  /**
   * GET /api/stock/book-copies/{bookCopyId}?t=2024-01-15T10:30:00
   * Retourne la quantité en stock pour une BookCopy précise à un instant t.
   */
  @GetMapping("/book-copies/{bookCopyId}")
  public ResponseEntity<Integer> getStockForBookCopyAt(
          @PathVariable UUID bookCopyId,
          @RequestParam(required = false)
          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
          DateTime t) {
    DateTime time = (t != null) ? t : DateTime.now();
    Integer quantity = stockService.getStockForBookCopyAt(bookCopyId, time);
    if (quantity == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(quantity);
  }

  /**
   * GET /api/stock/books/{bookId}/book-copies?t=2024-01-15T10:30:00
   * Retourne le stock de toutes les copies d'un livre donné à un instant t.
   */
  @GetMapping("/books/{bookId}/book-copies")
  public ResponseEntity<Map<BookCopy, Integer>> getBookCopyStockForBookAt(
          @PathVariable UUID bookId,
          @RequestParam(required = false)
          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
          DateTime t) {
    DateTime time = (t != null) ? t : DateTime.now();
    return ResponseEntity.ok(stockService.getBookCopyStockForBookAt(bookId, time));
  }

  /**
   * POST /api/stock/arrivals
   * Enregistre une nouvelle arrivée de livres.
   * Body : objet Arrival en JSON.
   */
  @PostMapping("/arrivals")
  public ResponseEntity<Void> createArrival(@RequestBody Arrival arrival) {
    stockService.createArrival(arrival);
    return ResponseEntity.status(201).build();
  }

  /**
   * POST /api/stock/sales
   * Enregistre une vente (vérifie le stock disponible avant de persister).
   * Body : objet Sale en JSON.
   * Retourne 400 si stock insuffisant.
   */
  @PostMapping("/sales")
  public ResponseEntity<String> createSale(@RequestBody Sale sale) {
    try {
      stockService.createSell(sale);
      return ResponseEntity.status(201).build();
    } catch (IllegalArgumentException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
}
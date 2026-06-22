package com.hei.prog.services;

import com.hei.prog.entity.*;
import com.hei.prog.repository.ArrivalRepository;
import com.hei.prog.repository.SaleRepository;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockService {
  private final ArrivalRepository arrivalRepository;
  private final SaleRepository saleRepository;
  private final BookCopyService bookCopyService;

  public Map<UUID, Integer> getStockAt(DateTime t) {
    Map<UUID, Integer> stock = new HashMap<>();

    for (Arrival arrival : arrivalRepository.getArrivalsAt(t)) {
      for (CopyBookMovement cbm : arrival.getCopyBooks()) {
        UUID copyId = cbm.getBookCopy().getId();
        stock.merge(copyId, cbm.getNumber(), Integer::sum);
      }
    }

    for (Sale sale : saleRepository.getSalesAt(t)) {
      for (CopyBookMovement cbm : sale.getCopyBooks()) {
        UUID copyId = cbm.getBookCopy().getId();
        stock.merge(copyId, -cbm.getNumber(), Integer::sum);
      }
    }

    return stock;
  }

  public Map<BookCopy, Integer> getBookCopyStockAt(DateTime t){
    Map<BookCopy, Integer> result = new HashMap<>();
    var stock = getStockAt(t);
    for (var id : stock.keySet()){
      result.put(bookCopyService.getById(id), stock.get(id));
    }
    return result;
  }

  public Integer getStockForBookCopyAt(UUID book_copy_id, DateTime t){
    var stock = getStockAt(t);
    return stock.get(book_copy_id);
  }

  public Map<BookCopy, Integer> getBookCopyStockForBookAt(UUID book_id, DateTime t){
    Map<BookCopy, Integer> result = new HashMap<>();
    var stock = getBookCopyStockAt(t);
    for ( var bc : stock.keySet()){
      if (bc.getBook().getId() == book_id){
        result.put(bc, stock.get(bc));
      }
    }
    return result;
  }

  /*
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
  **/
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
    Map<UUID, Integer> stock = getStockAt(DateTime.now());

    for (CopyBookMovement c : sale.getCopyBooks()) {
      UUID copyId = c.getBookCopy().getId();
      int available = stock.getOrDefault(copyId, 0);

      if (available < c.getNumber()) {
        throw new IllegalArgumentException(
            "Not enough stock for BookCopy "
                + copyId
                + ": requested "
                + c.getNumber()
                + ", available "
                + available);
      }
    }

    saleRepository.save(sale);
  }
}

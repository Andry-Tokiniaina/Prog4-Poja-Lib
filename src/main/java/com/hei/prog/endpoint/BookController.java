package com.hei.prog.endpoint;

import com.hei.prog.entity.Book;
import com.hei.prog.entity.enums.Category;
import com.hei.prog.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable String id) {
        return bookService.getBookById(id);
    }

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookService.createBook(book);
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable String id, @RequestBody Book book) {
        return bookService.updateBook(id, book);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable String id) {
        bookService.deleteBook(id);
    }

    @GetMapping("/search/title")
    public List<Book> searchByTitle(@RequestParam String title) {
        return bookService.getBooksByTitle(title);
    }

    @GetMapping("/search/title-contains")
    public List<Book> searchByTitleContaining(@RequestParam String keyword) {
        return bookService.getBooksByTitleContaining(keyword);
    }

    @GetMapping("/search/author")
    public List<Book> searchByAuthor(@RequestParam String author) {
        return bookService.getBooksByAuthor(author);
    }

    @GetMapping("/search/author-contains")
    public List<Book> searchByAuthorContaining(@RequestParam String keyword) {
        return bookService.getBooksByAuthorContaining(keyword);
    }

    @GetMapping("/search/category")
    public List<Book> searchByCategory(@RequestParam Category category) {
        return bookService.getBooksByCategory(category);
    }

    @GetMapping("/search/category-title")
    public List<Book> searchByCategoryAndTitle(@RequestParam Category category, @RequestParam String keyword) {
        return bookService.getBooksByCategoryAndTitle(category, keyword);
    }

    @GetMapping("/search/author-category")
    public List<Book> searchByAuthorAndCategory(@RequestParam String author, @RequestParam Category category) {
        return bookService.getBooksByAuthorAndCategory(author, category);
    }

    @GetMapping("/count/category")
    public long countByCategory(@RequestParam Category category) {
        return bookService.countBooksByCategory(category);
    }
}
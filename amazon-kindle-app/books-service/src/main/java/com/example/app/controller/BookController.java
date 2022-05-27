package com.example.app.controller;

import com.example.app.model.Book;
import com.example.app.repository.BookRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class BookController {
    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @PostMapping("/books")
    public Mono<Book> addNewBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @GetMapping("/books")
    public Flux<Book> getAllBooks(
            @RequestParam(required = false, defaultValue = "ASC") Direction sortOrder,
            @RequestParam(required = false, defaultValue = "price") String sortedBy) {
        return bookRepository.findAll(Sort.by(sortOrder, sortedBy));
    }

    @GetMapping("/books/{id}")
    public Mono<Book> getBooksById(@PathVariable String id) {
        return bookRepository.findById(id);
    }
}

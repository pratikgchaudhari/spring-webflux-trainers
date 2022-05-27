package com.example.app.repository;

import com.example.app.model.Book;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface BookRepository extends ReactiveMongoRepository<Book, String> {
    @Override
    Mono<Book> findById(String s);
}

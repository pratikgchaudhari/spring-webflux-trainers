package hello.spring.webflux;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

@DataMongoTest
class ProductsRepositoryTest {

    ProductsRepository productsRepository;

    @BeforeEach
    void setup() {
        List<ProductInfo> products = List.of(
                new ProductInfo("id1", "product1", 20.0),
                new ProductInfo("id2", "product2", 40.0)
        );
        productsRepository.saveAll(products).blockLast();
    }

    @Test
    void findById() {
      Mono<ProductInfo> result = productsRepository.findById("id1").log();

      StepVerifier.create(result)
              .expectNextCount(1)
              .consumeNextWith( product ->
                      Assertions.assertEquals(product.productId, "id1")
              ).verifyComplete();
    }
}
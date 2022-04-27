package hello.project.reactor;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductServiceTest {
    ProductService productService = new ProductService();
    @Test
    void searchProducts() {
        Flux<ProductModel> result = productService.searchProducts("chocolate");

        StepVerifier.create(result)
                .consumeNextWith(product -> assertEquals(product.productType,"chocolate"))
                .consumeNextWith(product -> assertEquals(product.productType,"chocolate"))
                .verifyComplete();
    }

    @Test
    void searchProductsForInvalidType() {
        Flux<ProductModel> result = productService.searchProducts("icecream");

        StepVerifier.create(result)
                .expectNextCount(2)
                .verifyComplete();
    }

}
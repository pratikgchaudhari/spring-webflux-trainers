package hello.spring.webflux;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepository extends ReactiveMongoRepository<ProductInfo, String> {
}

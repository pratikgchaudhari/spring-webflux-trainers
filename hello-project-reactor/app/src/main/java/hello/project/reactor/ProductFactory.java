package hello.project.reactor;

import reactor.core.publisher.Flux;

import java.util.List;


public class ProductFactory {

    public static Flux<ProductModel> getGoodsProducts() {
        return Flux.fromIterable(List.of(
                new ProductModel("mobile", "electronics"),
                new ProductModel("shirt", "clothes"),
                new ProductModel("cadbury", "chocolate")
        ));
    }

    public static Flux<ProductModel> getPantryProducts() {
        return Flux.fromIterable(List.of(
                new ProductModel("potato", "vegetables"),
                new ProductModel("nestle", "chocolate")
        ));
    }

    public static Flux<ProductModel> getDefaultProducts() {
        return Flux.fromIterable(List.of(
                new ProductModel("laptop", "electronics"),
                new ProductModel("apple", "fruits")
        ));
    }
}

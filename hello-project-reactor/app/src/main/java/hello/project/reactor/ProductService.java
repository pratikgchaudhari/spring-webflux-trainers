package hello.project.reactor;

import reactor.core.publisher.Flux;

public class ProductService {

    public Flux<ProductModel> searchProducts(String choice) {
        Flux<ProductModel> goodsProducts = ProductFactory.getGoodsProducts();
        Flux<ProductModel> pantryProducts = ProductFactory.getPantryProducts();
        Flux<ProductModel> defaultProducts = ProductFactory.getDefaultProducts();

        return goodsProducts
                .mergeWith(pantryProducts)
                .filter(product -> product.productType.equals(choice))
                .switchIfEmpty(defaultProducts)
                .log();
    }
}

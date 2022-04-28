package hello.spring.webflux;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ProductInfo {
   String productId;
   String productName;
   Double price;

   public ProductInfo(String productId, String productName, Double price) {
      this.productId = productId;
      this.productName = productName;
      this.price = price;
   }
}

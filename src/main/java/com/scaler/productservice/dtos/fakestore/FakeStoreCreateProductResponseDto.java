package com.scaler.productservice.dtos.fakestore;

import com.scaler.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreCreateProductResponseDto {
   private long id;
   private String title;
   private String type;
   private String description;
   private String image;
   private String category;

   private double price;
   public Product toProduct(){
      Product product = new Product();

      product.setDescription(this.description);
      product.setTitle(this.title);
      product.setPrice(this.price);
      product.setCategory(this.type);
      product.setImageUrl(this.image);
      return  product;
   }


}

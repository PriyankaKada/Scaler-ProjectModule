package com.scaler.productservice.dtos;

import com.scaler.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

/*DTO are data transfer objects only purpose od these objects
 * is either to receive data of send data from outside
 * */

@Getter
@Setter
public class CreateProductRequestDto {
  private   String title;
    private String description;
   private String type;
   private double price;
   private String imgUrl;


  public Product toProduct(){
      Product product = new Product();

      product.setDescription(this.description);
      product.setTitle(this.title);
      product.setPrice(this.price);
      product.setCategory(this.type);
      product.setImageUrl(this.imgUrl);
        return  product;
  }
}

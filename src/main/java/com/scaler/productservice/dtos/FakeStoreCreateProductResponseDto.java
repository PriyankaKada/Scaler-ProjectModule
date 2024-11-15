package com.scaler.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreCreateProductResponseDto {
   private long id;
   private String title;
   private String description;
   private String image;
   private String category;
   private double price;

}

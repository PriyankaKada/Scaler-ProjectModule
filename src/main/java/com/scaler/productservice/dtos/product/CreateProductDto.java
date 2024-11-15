package com.scaler.productservice.dtos.product;

import com.scaler.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductDto {

    private long id;
    private String title;
    private String description;

    private  double price;
    private String imageUrl;

    public static CreateProductDto fromProduct(Product product){
        CreateProductDto responseDto =new CreateProductDto();
        responseDto.setDescription(product.getDescription());
        responseDto.setTitle(product.getTitle());
        responseDto.setPrice(product.getPrice());
        responseDto.setImageUrl(product.getImageUrl());
        responseDto.setId(product.getId());
        return responseDto;
    }
}

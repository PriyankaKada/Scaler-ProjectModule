package com.scaler.productservice.dtos.product;

import com.scaler.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetProductDto {
    private long id;
    private String title;
    private String description;

    private  double price;
    private String imageUrl;
    private String category;

    public static GetProductDto from(Product product) {
        GetProductDto getProductResponseDto = new GetProductDto();
        getProductResponseDto.setId(product.getId());
        getProductResponseDto.setDescription(product.getDescription());
        getProductResponseDto.setImageUrl(product.getImageUrl());
        getProductResponseDto.setPrice(product.getPrice());
        getProductResponseDto.setTitle(product.getTitle());
        getProductResponseDto.setCategory(product.getCategory());

        return getProductResponseDto;
    }
}

package com.scaler.productservice.dtos.product;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetAllProductsResponseDto {
    private List<GetProductDto> products;
}
package com.scaler.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponseDto {
    int statusCode;
    String message;
}

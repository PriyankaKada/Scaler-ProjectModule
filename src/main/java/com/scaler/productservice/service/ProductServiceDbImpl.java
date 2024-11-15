package com.scaler.productservice.service;

import com.scaler.productservice.dtos.product.PatchProductResponseDto;
import com.scaler.productservice.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("databaseProductService")
public class ProductServiceDbImpl implements ProductService{

    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public PatchProductResponseDto updateProduct(Long id) {
        return null;
    }
}

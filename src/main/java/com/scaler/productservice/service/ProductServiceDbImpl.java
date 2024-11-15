package com.scaler.productservice.service;

import com.scaler.productservice.models.Product;
import org.springframework.stereotype.Service;

@Service("databaseProductService")
public class ProductServiceDbImpl implements ProductService{

    @Override
    public Product createProduct(Product product) {
        return null;
    }
}

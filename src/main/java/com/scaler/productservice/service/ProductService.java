package com.scaler.productservice.service;


import com.scaler.productservice.dtos.product.PatchProductResponseDto;
import com.scaler.productservice.models.Product;

import java.util.List;

/*Service Should never take DTO object as parameter
Service has business logic, and it may be used by multiple endpoints
* it should always take exact attributes
 because this can be reused in some other functionality also
* and DTO is very specific to request we can use Model object
*
* */
public interface ProductService {
     Product createProduct(Product product);
     List<Product> getAllProducts();

     PatchProductResponseDto updateProduct(Long id);
}

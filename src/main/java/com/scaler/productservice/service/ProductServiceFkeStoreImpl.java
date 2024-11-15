package com.scaler.productservice.service;

import com.scaler.productservice.dtos.fakestore.FakeStoreCreateProductResponseDto;
import com.scaler.productservice.dtos.product.GetProductDto;
import com.scaler.productservice.dtos.product.PatchProductResponseDto;
import com.scaler.productservice.models.Product;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service("fakeStoreProductService")
/* If we have more that one objects we are telling spring to use this as primary object
 * */
@Primary
/* When we annotate with @Service we will tell spring
 * please create object of this class and keep it inside the container
 * */
public class ProductServiceFkeStoreImpl implements ProductService{

    private RestTemplate restTemplate;

    public ProductServiceFkeStoreImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product createProduct(Product product) {

        FakeStoreCreateProductResponseDto fakeStoreCreateProductRequestDto = new FakeStoreCreateProductResponseDto();

        fakeStoreCreateProductRequestDto.setCategory(product.getCategory());
        fakeStoreCreateProductRequestDto.setDescription(product.getDescription());
        fakeStoreCreateProductRequestDto.setTitle(product.getTitle());
        fakeStoreCreateProductRequestDto.setPrice(product.getPrice());
        fakeStoreCreateProductRequestDto.setImage(product.getImageUrl());

      FakeStoreCreateProductResponseDto fakeStoreCreateProductResponseDto =  restTemplate.postForObject(
            "https://fakestoreapi.com/products",
              fakeStoreCreateProductRequestDto,
                FakeStoreCreateProductResponseDto.class

        );

      Product product1 =new Product();

        assert fakeStoreCreateProductResponseDto != null;
        product1.setImageUrl(fakeStoreCreateProductResponseDto.getImage());
        product1.setId(fakeStoreCreateProductResponseDto.getId());
        product1.setCategory(fakeStoreCreateProductResponseDto.getCategory());
        product1.setTitle(fakeStoreCreateProductResponseDto.getTitle());
        product1.setPrice(fakeStoreCreateProductResponseDto.getPrice());
        product1.setDescription(fakeStoreCreateProductRequestDto.getDescription());


        return product1;
    }

    @Override
    public List<Product> getAllProducts() {

        FakeStoreCreateProductResponseDto[] fakeStoreCreateProductResponseDtos= restTemplate.getForObject(
                "https://fakestoreapi.com/products",
                FakeStoreCreateProductResponseDto[].class);
        List<FakeStoreCreateProductResponseDto> fakeStoreCreateProductResponseDtos1 = Arrays.stream(fakeStoreCreateProductResponseDtos).toList();

        List<Product> products =new ArrayList<>();

        for (FakeStoreCreateProductResponseDto fakeStoreCreateProductResponseDto: fakeStoreCreateProductResponseDtos1){
           products.add(fakeStoreCreateProductResponseDto.toProduct());
        }

        return products;
    }

    @Override
    public PatchProductResponseDto updateProduct(Long id) {
        throw  new RuntimeException();
    }

    @Override
    public Product getSingleProduct(Long productId) {
        GetProductDto product = restTemplate.getForObject("https://fakestoreapi.com/products/"+productId,
                GetProductDto.class);

        Product p = new Product();
        assert product != null;
        p.setDescription(product.getDescription());
        p.setId(product.getId());
        p.setImageUrl(product.getImageUrl());
        p.setTitle(product.getTitle());
        p.setCategory(product.getCategory());
        p.setPrice(product.getPrice());


        return p;
    }
}

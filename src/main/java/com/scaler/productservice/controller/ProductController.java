package com.scaler.productservice.controller;

import com.scaler.productservice.dtos.ErrorResponseDto;
import com.scaler.productservice.dtos.product.*;
import com.scaler.productservice.models.Product;
import com.scaler.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
/** [RestController] is special annotation which will be make this class act like a controller
 * where different methods of this class will be used for different types of requests
 *
 * It Makes class able to receive requests from dispatcher servlet, Dispatcher servlet will consider this
 * class as one of the potential class to handle upcoming requests
 *
 * */
@RequestMapping("/products/")
public class ProductController {


//    @Value("${productService}")
//    private String productServiceType;

//    @Qualifier()
    ProductService productService;

    ProductController(@Qualifier("fakeStoreProductService") ProductService productService){
        this.productService=productService;
    }
    @PostMapping()

    public CreateProductResponseDto createProduct(@RequestBody CreateProductRequestDto createProductRequestDto){
        Product product = productService.createProduct(createProductRequestDto.toProduct());
        CreateProductResponseDto createProductResponseDto = new CreateProductResponseDto();

        createProductResponseDto.setId(product.getId());
        createProductResponseDto.setPrice(product.getPrice());
        createProductResponseDto.setTitle(product.getTitle());
        createProductResponseDto.setDescription(product.getDescription());
        createProductResponseDto.setImageUrl(product.getImageUrl());

        return createProductResponseDto;
    }

    @GetMapping()
    public GetAllProductsResponseDto getProducts(){
        List<Product> products = productService.getAllProducts();
        GetAllProductsResponseDto response = new GetAllProductsResponseDto();

        List<GetProductDto> getProductResponseDtos = new ArrayList<>();

        for (Product product: products) {
            getProductResponseDtos.add(GetProductDto.from(product));
        }
        response.setProducts(getProductResponseDtos);
        return response;

    }

    @GetMapping("{id}")
    public GetProductDto getSingleProducts(@PathVariable("id") Long productId){
        Product p = productService.getSingleProduct(productId);

        return GetProductDto.from(p);

    }

    @RequestMapping(name = "PRIYANKA")
    public String magicMethod(){
        return "Magic";
    }

    @PatchMapping("{id}")
    public PatchProductResponseDto patch(@PathVariable("id")Long id){
        return  productService.updateProduct(id);
    }

    @ExceptionHandler(Exception.class)
    public String handleException(){
        return "Something Went Wrong";
    }

    @ExceptionHandler(RuntimeException.class)
    public ErrorResponseDto handleRunTimeException(RuntimeException e){
       ErrorResponseDto errorResponseDto = new ErrorResponseDto();
       errorResponseDto.setMessage("Runtime Exception inside Product Controller"+e.getLocalizedMessage());
       errorResponseDto.setStatusCode(234);
       return  errorResponseDto;
    }

}

package com.scaler.productservice.controller;

import com.scaler.productservice.dtos.CreateProductRequestDto;
import com.scaler.productservice.dtos.CreateProductResponseDto;
import com.scaler.productservice.models.Product;
import com.scaler.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

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
    public String getProducts(){
        return "Here is list of your product: ";

    }

    @GetMapping("{id}")
    public String getSingleProducts(@PathVariable("id") Long productId){
        return  "Here is your Product "+productId;

    }

    @RequestMapping(name = "PRIYANKA")
    public String magicMethod(){
        return "Magic";
    }
}

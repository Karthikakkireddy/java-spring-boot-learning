package com.example.demoProject.Controller;

import com.example.demoProject.DTO.ProductRequest;
import com.example.demoProject.DTO.ProductResponse;
import com.example.demoProject.DTO.UserRequest;
import com.example.demoProject.DTO.UserResponse;
import com.example.demoProject.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ProductController
{
    private final ProductService productService;


    @PostMapping("/product")
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest productRequest)
    {
        ProductResponse savedProductResponse = productService.createProductService(productRequest);

        return new ResponseEntity<>(savedProductResponse, HttpStatus.CREATED);


    }

    @PutMapping("/product/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@RequestBody ProductRequest productRequest,
                                                         @PathVariable Long id)
    {
        ProductResponse savedProductResponse = productService.updateProductService(productRequest, id);

        return new ResponseEntity<>(savedProductResponse, HttpStatus.OK);


    }
}

package com.example.demoProject.Controller;

import com.example.demoProject.DTO.ProductRequest;
import com.example.demoProject.DTO.ProductResponse;

import com.example.demoProject.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController
{
    private final ProductService productService;


    @PostMapping("/product")
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody ProductRequest productRequest)
    {
        ProductResponse savedProductResponse = productService.createProductService(productRequest);

        return new ResponseEntity<>(savedProductResponse, HttpStatus.CREATED);


    }

    @PutMapping("/product/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@Valid @RequestBody ProductRequest productRequest,
                                                         @PathVariable Long id)
    {
        ProductResponse savedProductResponse = productService.updateProductService(productRequest, id);

        return new ResponseEntity<>(savedProductResponse, HttpStatus.OK);

    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id)
    {
        ProductResponse fetchedProductResponse = productService.getProductByIdService(id);

        return new ResponseEntity<>(fetchedProductResponse, HttpStatus.OK);
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductResponse>> getProducts()
    {
        List<ProductResponse> products = productService.getProductService();

        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    @DeleteMapping("/product/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id)
    {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
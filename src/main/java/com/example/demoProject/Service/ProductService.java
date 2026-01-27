package com.example.demoProject.Service;


import com.example.demoProject.DTO.ProductRequest;
import com.example.demoProject.DTO.ProductResponse;
import com.example.demoProject.Exceptions.ProductNotFoundException;
import com.example.demoProject.Model.Product;
import com.example.demoProject.Repository.ProductsRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService
{
    private final ProductsRepo productsRepo;

    public ProductResponse createProductService(ProductRequest productRequest)
    {
        Product newProduct = mapToProductEntity(productRequest);

        Product savedProduct = productsRepo.save(newProduct);

        ProductResponse savedProductResponse = mapToProductResponse(savedProduct);

        return savedProductResponse;
    }


    public ProductResponse updateProductService(ProductRequest productRequest, Long id)
    {
        Product oldDataProduct = productsRepo.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        oldDataProduct.setName(productRequest.getName());
        oldDataProduct.setDescription(productRequest.getDescription());
        oldDataProduct.setActive(productRequest.getActive());
        oldDataProduct.setPrice(productRequest.getPrice());
        oldDataProduct.setStockQuantity(productRequest.getStockQuantity());
        oldDataProduct.setCategory(productRequest.getCategory());
        oldDataProduct.setImageUrl(productRequest.getImageUrl());


        Product updatedProduct = productsRepo.save(oldDataProduct);


        return mapToProductResponse(updatedProduct);
    }

    public ProductResponse getProductByIdService(Long id)
    {
        Product fetchedProduct = productsRepo.findById(id).orElseThrow(() -> new ProductNotFoundException(id));

        return mapToProductResponse(fetchedProduct);
    }

    public List<ProductResponse> getProductService()
    {
        List<Product> productList = productsRepo.findAll();

        List<ProductResponse> productResponseList = productList.stream()
                .map(product -> mapToProductResponse(product))
                .toList();

        return productResponseList;
    }

    public void deleteProduct(Long id)
    {
        Product product = productsRepo.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        productsRepo.delete(product);
    }


    private Product mapToProductEntity(ProductRequest productRequest)
    {
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice( productRequest.getPrice());
        product.setStockQuantity(productRequest.getStockQuantity());
        product.setCategory(productRequest.getCategory());
        product.setImageUrl(productRequest.getImageUrl());

        return product;

    }

    private ProductResponse mapToProductResponse(Product product)
    {
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setName(product.getName());
        productResponse.setDescription(product.getDescription());
        productResponse.setPrice( product.getPrice());
        productResponse.setStockQuantity(product.getStockQuantity());
        productResponse.setCategory(product.getCategory());
        productResponse.setImageUrl(product.getImageUrl());
        productResponse.setActive(product.getActive());
        return productResponse;

    }
}

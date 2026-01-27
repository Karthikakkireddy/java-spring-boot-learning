package com.example.demoProject.Service;


import com.example.demoProject.DTO.ProductRequest;
import com.example.demoProject.DTO.ProductResponse;
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
        Product newProduct = new Product(
                productRequest.getName(),
                productRequest.getDescription(),
                productRequest.getPrice(),
                productRequest.getStockQuantity(),
                productRequest.getCategory(),
                productRequest.getImageUrl()
        );

        Product savedProduct = productsRepo.save(newProduct);


        return new ProductResponse(
                savedProduct.getId(),
                savedProduct.getName(),
                savedProduct.getDescription(),
                savedProduct.getPrice(),
                savedProduct.getStockQuantity(),
                savedProduct.getCategory(),
                savedProduct.getImageUrl(),
                savedProduct.getActive()
        );
    }


    public ProductResponse updateProductService(ProductRequest productRequest, Long id)
    {
        Product oldDataProduct = productsRepo.findById(id).orElse(null);
        oldDataProduct.setName(productRequest.getName());
        oldDataProduct.setDescription(productRequest.getDescription());
        oldDataProduct.setActive(productRequest.getActive());
        oldDataProduct.setPrice(productRequest.getPrice());
        oldDataProduct.setStockQuantity(productRequest.getStockQuantity());
        oldDataProduct.setCategory(productRequest.getCategory());
        oldDataProduct.setImageUrl(productRequest.getImageUrl());


        Product updatedProduct = productsRepo.save(oldDataProduct);


        return new ProductResponse(
                updatedProduct.getId(),
                updatedProduct.getName(),
                updatedProduct.getDescription(),
                updatedProduct.getPrice(),
                updatedProduct.getStockQuantity(),
                updatedProduct.getCategory(),
                updatedProduct.getImageUrl(),
                updatedProduct.getActive()
        );
    }

    public ProductResponse getProductByIdService(Long id)
    {
        Product fetchedProduct = productsRepo.findById(id).orElse(null);

        return new ProductResponse(
                fetchedProduct.getId(),
                fetchedProduct.getName(),
                fetchedProduct.getDescription(),
                fetchedProduct.getPrice(),
                fetchedProduct.getStockQuantity(),
                fetchedProduct.getCategory(),
                fetchedProduct.getImageUrl(),
                fetchedProduct.getActive()
        );
    }

    public List<ProductResponse> getProductService()
    {
        List<Product> productList = productsRepo.findAll();

        List<ProductResponse> productResponseList = productList.stream()
                .map(
                        product -> {
                            ProductResponse productResponse = new ProductResponse(
                                    product.getId(),
                                    product.getName(),
                                    product.getDescription(),
                                    product.getPrice(),
                                    product.getStockQuantity(),
                                    product.getCategory(),
                                    product.getImageUrl(),
                                    product.getActive()
                            );
                            return productResponse;
                        }
                ).toList();

        return productResponseList;
    }

    public void deleteProduct(Long id)
    {
        productsRepo.deleteById(id);
    }
}

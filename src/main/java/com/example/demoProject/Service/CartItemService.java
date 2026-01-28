package com.example.demoProject.Service;

import com.example.demoProject.DTO.CartItemRequest;
import com.example.demoProject.Exceptions.ProductNotFoundException;
import com.example.demoProject.Exceptions.UserNotFoundException;
import com.example.demoProject.Model.CartItem;
import com.example.demoProject.Model.Product;
import com.example.demoProject.Model.Users;
import com.example.demoProject.Repository.CartItemRepo;
import com.example.demoProject.Repository.ProductsRepo;
import com.example.demoProject.Repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
@RequiredArgsConstructor
public class CartItemService
{

    private final ProductsRepo productsRepo;
    private final UserRepo userRepo;
    private final CartItemRepo cartItemRepo;

    public boolean addToCartService(String userId, CartItemRequest request)
    {
        Product product = productsRepo.findById(request.getProductId()).orElseThrow(() -> new ProductNotFoundException(request.getProductId()));

        if(product.getStockQuantity() < request.getQuantity())
        {
            return false;
        }

        Users user = userRepo.findById(Long.valueOf(userId)).orElseThrow(() -> new UserNotFoundException(Long.valueOf(userId)));

        CartItem cartItem = cartItemRepo.findByUserAndProduct(user, product);

        if(cartItem != null)
        {
            cartItem.setQuantity(cartItem.getQuantity() + request.getQuantity());
            cartItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())));
            cartItemRepo.save(cartItem);
        }
        else {
            CartItem newCartItem = new CartItem();
            newCartItem.setUser(user);
            newCartItem.setProduct(product);
            newCartItem.setQuantity(request.getQuantity());
            newCartItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(request.getQuantity())));
            cartItemRepo.save(newCartItem);
        }
        return true;
    }

    public boolean removeItemCartService(String userId, Long productId)
    {
        Product product = productsRepo.findById(productId).orElseThrow(() -> new ProductNotFoundException(productId));
        Users user = userRepo.findById(Long.valueOf(userId)).orElseThrow(() -> new UserNotFoundException(Long.valueOf(userId)));

        CartItem cartItem = cartItemRepo.findByUserAndProduct(user, product);

        if (cartItem == null) {
            return false; // item not in cart
        }

        cartItemRepo.delete(cartItem);
        return true;

    }
}

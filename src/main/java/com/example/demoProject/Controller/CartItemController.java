package com.example.demoProject.Controller;

import com.example.demoProject.DTO.CartItemRequest;
import com.example.demoProject.Model.Product;
import com.example.demoProject.Service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CartItemController
{

    private final CartItemService cartService;

    @PostMapping("/addCart")
    public ResponseEntity<Void> addToCart (
            @RequestHeader("X-User_ID") String userId,
            @RequestBody CartItemRequest request
    )
    {

        boolean added = cartService.addToCartService(userId, request);

        return added
                ? ResponseEntity.status(HttpStatus.CREATED).build()
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }

    @DeleteMapping("/deleteItem/{productId}")
    public ResponseEntity<Void> removeFromCart(
            @RequestHeader("X-User_ID") String userId,
           @PathVariable Long productId
    )
    {
        boolean removed = cartService.removeItemCartService(userId, productId);

        return removed
                ? ResponseEntity.status(HttpStatus.NO_CONTENT).build()
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }



}

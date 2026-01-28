package com.example.demoProject.Controller;

import com.example.demoProject.DTO.CartItemRequest;
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

}

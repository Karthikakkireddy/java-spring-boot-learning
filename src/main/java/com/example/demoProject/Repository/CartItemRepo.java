package com.example.demoProject.Repository;

import com.example.demoProject.Model.CartItem;
import com.example.demoProject.Model.Product;
import com.example.demoProject.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepo extends JpaRepository<CartItem, Long>
{
    CartItem findByUserAndProduct(Users user, Product product);
}

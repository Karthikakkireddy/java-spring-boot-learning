package com.example.demoProject.Repository;


import com.example.demoProject.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepo extends JpaRepository<Product, Long>
{
}

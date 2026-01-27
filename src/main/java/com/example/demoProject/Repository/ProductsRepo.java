package com.example.demoProject.Repository;


import com.example.demoProject.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepo extends JpaRepository<Product, Long>
{
    @Query("""
    SELECT p FROM Product p
    WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%'))
       OR LOWER(p.description) LIKE LOWER(CONCAT('%', :keyword, '%'))
    """)
    List<Product> searchByKeyword(@Param("keyword") String keyword);

    List<Product> findByActiveTrue();

}

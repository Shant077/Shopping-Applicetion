package com.example.demo.repository;

import com.example.demo.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductsRepository extends JpaRepository<Products, Long> {
    Products findProductsById(Long id);

    Products findProductsByPictureUrl(String name);
    Products findProductsByName(String name);

    void deleteById(Long id);

}

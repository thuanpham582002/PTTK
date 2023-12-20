package com.thuan.pttk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.thuan.pttk.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAll();

    @Query("SELECT p FROM Product p WHERE TYPE(p) = Book")
    List<Product> findAllBookProduct();

    @Query("SELECT p FROM Product p WHERE TYPE(p) IN (MobilePhone)")
    List<Product> findAllMobilePhoneProduct();

    @Query("SELECT p FROM Product p WHERE TYPE(p) = Clothes")
    List<Product> findAllClothesProduct();
}

package com.thuan.pttk.service;

import java.util.List;
import java.util.Optional;

import com.thuan.pttk.entity.Product;

public interface ProductService {
    List<Product> findAll();
    Optional<Product> findById(long productId);
    List<Product> getAllBookProduct();
    List<Product> getAllElectronicProduct();
    List<Product> getAllClothesProduct();
    List<Product> getAllShoesProduct();
}

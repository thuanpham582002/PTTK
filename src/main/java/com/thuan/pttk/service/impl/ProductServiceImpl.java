package com.thuan.pttk.service.impl;

import java.util.List;
import java.util.Optional;

import com.thuan.pttk.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thuan.pttk.entity.Product;
import com.thuan.pttk.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(long productId) {
        return productRepository.findById(productId);
    }

    @Override
    public List<Product> getAllBookProduct() {
        return productRepository.findAllBookProduct();
    }

    @Override
    public List<Product> getAllElectronicProduct() {
        return productRepository.findAllElectronicProduct();
    }

    @Override
    public List<Product> getAllClothesProduct() {
        return productRepository.findAllClothesProduct();
    }

    @Override
    public List<Product> getAllShoesProduct() {
        return productRepository.findAllShoesProduct();
    }

}

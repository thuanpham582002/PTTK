package com.thuan.pttk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thuan.pttk.entity.clothes.Clothes;

@Repository
public interface ClothesRepository extends JpaRepository<Clothes, Long>  {
    
}

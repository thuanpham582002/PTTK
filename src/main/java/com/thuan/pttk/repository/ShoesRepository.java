package com.thuan.pttk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thuan.pttk.entity.shoes.Shoes;

@Repository
public interface ShoesRepository extends JpaRepository<Shoes, Long>  {
    
}

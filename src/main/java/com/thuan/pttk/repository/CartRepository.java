package com.thuan.pttk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thuan.pttk.entity.cart.Cart;
@Repository
public interface CartRepository extends JpaRepository<Cart, Long>{

}

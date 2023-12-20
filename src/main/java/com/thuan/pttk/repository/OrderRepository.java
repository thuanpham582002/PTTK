package com.thuan.pttk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thuan.pttk.entity.order.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{
    
}

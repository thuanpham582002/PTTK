package com.thuan.pttk.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thuan.pttk.entity.User;
import com.thuan.pttk.entity.cutstomer.Customer;
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
    Optional<Customer> findByUser(User user);
    
}

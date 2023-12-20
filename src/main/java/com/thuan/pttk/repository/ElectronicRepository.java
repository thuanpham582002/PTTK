package com.thuan.pttk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thuan.pttk.entity.electronic.Electronic;

@Repository
public interface ElectronicRepository extends JpaRepository<Electronic, Long>  {
    
}

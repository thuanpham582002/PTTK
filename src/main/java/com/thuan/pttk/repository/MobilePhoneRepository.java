package com.thuan.pttk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thuan.pttk.entity.mobile.MobilePhone;

@Repository
public interface MobilePhoneRepository extends JpaRepository<MobilePhone, Long>  {
    
}

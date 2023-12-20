package com.thuan.pttk.service;

import com.thuan.pttk.entity.cutstomer.Customer;

public interface CustomerService {
    Customer getByUsername(String username);

    void save(Customer customer);
}

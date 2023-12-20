package com.thuan.pttk.service.impl;

import com.thuan.pttk.repository.CustomerRepository;
import com.thuan.pttk.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thuan.pttk.entity.cutstomer.Customer;
import com.thuan.pttk.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Customer getByUsername(String username) {
        return customerRepository.findByUser(userRepository.findByUsername(username).get()).get();
    }

    @Override
    public void save(Customer customer) {
        customerRepository.save(customer);
    }

}

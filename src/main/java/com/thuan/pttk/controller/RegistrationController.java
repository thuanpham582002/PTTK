package com.thuan.pttk.controller;

import com.thuan.pttk.entity.Role;
import com.thuan.pttk.entity.User;
import com.thuan.pttk.entity.cutstomer.Address;
import com.thuan.pttk.entity.cutstomer.Customer;
import com.thuan.pttk.entity.cutstomer.Fullname;
import com.thuan.pttk.repository.CustomerRepository;
import com.thuan.pttk.repository.RoleRepository;
import com.thuan.pttk.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password, @RequestParam String nation, @RequestParam String city, @RequestParam String district, @RequestParam String street, @RequestParam int numberHouse, @RequestParam String firstName, @RequestParam String middleName, @RequestParam String lastName) {
        // Get role user from database
        Role roleCustomer = roleRepository.findByName("ROLE_CUSTOMER");
        User user = User.builder().username(username)
                .password(passwordEncoder.encode(password))
                .accountNonExpired(true).accountNonLocked(true)
                .credentialsNonExpired(true).isEnabled(true)
                .roles(List.of(roleCustomer))
                .build();

        Fullname fullname = new Fullname();
        fullname.setFirstName(firstName);
        fullname.setMiddleName(middleName);
        fullname.setLastName(lastName);

        Address address = new Address();
        address.setNation(nation);
        address.setCity(city);
        address.setDistrict(district);
        address.setStreet(street);
        address.setNumberHouse(numberHouse);

        userRepository.save(user);
        customerRepository.save(Customer.builder().user(user).address(address).fullname(fullname).build());
        return "login";
    }
}
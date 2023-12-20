package com.thuan.pttk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.thuan.pttk.entity.cutstomer.Customer;
import com.thuan.pttk.security.UserDetailsCustom;
import com.thuan.pttk.service.CustomerService;

import jakarta.servlet.http.HttpServletRequest;


@Controller
public class LoginController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/login-page")
    public String loginPage() {
        return "login";
    }

    @GetMapping(value = "/403")
    public String page403() {
        return "403";
    }

    @GetMapping("/login-success")
    public String loginSuccess(@AuthenticationPrincipal UserDetailsCustom user, HttpServletRequest request) {
        if (user.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_CUSTOMER"))) {
            Customer customer = customerService.getByUsername(user.getUsername());
            request.getSession().setAttribute("fullname", customer.getFullname().getName());
            return "redirect:/home";
        }
        return null;
    }

    @GetMapping("/register")
    public String registerPage(){
        return "register";
    }

}

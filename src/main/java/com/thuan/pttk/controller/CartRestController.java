package com.thuan.pttk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thuan.pttk.dto.CartItemRequestDto;
import com.thuan.pttk.service.CartService;

@RestController
@RequestMapping("/cart")
@CrossOrigin("*")
public class CartRestController {
    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public int addToCart(@RequestBody CartItemRequestDto cartItemRequestDto) {
        return cartService.addToCart(cartItemRequestDto.getProductId(), cartItemRequestDto.getQuantity());
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updateCart(@RequestBody CartItemRequestDto cartItemRequestDto) {
        cartService.updateCart(cartItemRequestDto.getCartItemId(), cartItemRequestDto.getQuantity());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{cartItemId}")
    public ResponseEntity<Void> deleteCartItem(@PathVariable long cartItemId) {
        cartService.deleteCartItem(cartItemId);
        return ResponseEntity.ok().build();
    }
}

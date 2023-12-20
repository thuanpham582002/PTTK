package com.thuan.pttk.service;

import com.thuan.pttk.entity.cart.Cart;

public interface CartService {
    int addToCart(long productId, int quantity);
    Cart getCart();
    void updateCart(long cartItemId, int quantity);
    void deleteCartItem(long cartItemId);
}

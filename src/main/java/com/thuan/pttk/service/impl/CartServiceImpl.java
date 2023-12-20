package com.thuan.pttk.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.thuan.pttk.repository.CartItemRepository;
import com.thuan.pttk.repository.CartRepository;
import com.thuan.pttk.repository.CustomerRepository;
import com.thuan.pttk.repository.ProductRepository;
import com.thuan.pttk.security.UserDetailsCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.thuan.pttk.entity.Product;
import com.thuan.pttk.entity.cart.Cart;
import com.thuan.pttk.entity.cart.CartItem;
import com.thuan.pttk.entity.cutstomer.Customer;
import com.thuan.pttk.service.CartService;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public int addToCart(long productId, int quantity) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();

        Product product = productRepository.findById(productId).get();

        if (authentication != null && authentication.isAuthenticated()) {
            UserDetailsCustom userDetail = (UserDetailsCustom) authentication.getPrincipal();
            Customer customer = customerRepository.findByUser(userDetail.getUser()).get();
            Cart cart = customer.getCart();
            if (cart == null) {

                CartItem cartItem = CartItem.builder().product(product).quantity(quantity).build();
                cart = Cart.builder().customer(customer).build();

                cartItem.setCart(cart);
                cart.setCartItemList(new ArrayList<>(List.of(cartItem)));

                cart = cartRepository.save(cart);
                return cart.getCartItemList().size();
            } else {
                List<CartItem> cartItemList = cart.getCartItemList();
                int indexOfCartItemInList = existsProductInCart(cartItemList, productId);
                if (indexOfCartItemInList == -1) {
                    CartItem cartItem = CartItem.builder().product(product).quantity(quantity).cart(cart).build();
                    cart.addCartItem(cartItem);
                } else {
                    CartItem cartItem = cartItemList.get(indexOfCartItemInList);
                    cartItem.setQuantity(cartItem.getQuantity() + quantity);
                }
                cart = cartRepository.save(cart);
                return cart.getCartItemList().size();
            }
        } else {
            return -1;
        }
    }

    private int existsProductInCart(List<CartItem> cartItemsList, long productId) {
        for (CartItem ci : cartItemsList) {
            if (ci.getProduct().getId() == productId) {
                return cartItemsList.indexOf(ci);
            }
        }
        return -1;
    }

    @Override
    public Cart getCart() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            UserDetailsCustom userDetailsCusom = (UserDetailsCustom) authentication.getPrincipal();
            Customer customer = customerRepository.findByUser(userDetailsCusom.getUser()).get();
            Cart cart = customer.getCart();
            if (cart == null) {
                cart = Cart.builder().customer(customer).build();
                cart = cartRepository.save(cart);
            }
            return cart;
        }
        return null;
    }

    @Override
    public void updateCart(long cartItemId, int quantity) {
        CartItem cartItem = cartItemRepository.findById(cartItemId).get();
        cartItem.setQuantity(quantity);
        cartItemRepository.save(cartItem);
    }

    @Override
    public void deleteCartItem(long cartItemId) {
        Cart cart = getCart();
        List<CartItem> cartItems = cart.getCartItemList();
        for(CartItem cartItem : cartItems) {
            if(cartItem.getId() == cartItemId){
                cartItems.remove(cartItem);break;
            }
        }
        cart.setCartItemList(cartItems);
        cartRepository.save(cart);
    }

}

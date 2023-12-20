package com.thuan.pttk.controller;

import com.thuan.pttk.entity.Product;
import com.thuan.pttk.entity.book.Book;
import com.thuan.pttk.entity.cart.Cart;
import com.thuan.pttk.entity.clothes.Clothes;
import com.thuan.pttk.entity.mobile.MobilePhone;
import com.thuan.pttk.entity.order.Order;
import com.thuan.pttk.service.CartService;
import com.thuan.pttk.service.OrderService;
import com.thuan.pttk.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;



@Controller
public class HomeController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderService orderService;

    @GetMapping(value = { "/", "/home" })
    public String goPageHome(Model model) {
        model.addAttribute("page", "home");
        List<Product> productList = productService.findAll();
        List<Book> bookList = new ArrayList<>();
        List<Clothes> clothesList = new ArrayList<>();
        List<MobilePhone> mobilePhoneList = new ArrayList<>();

        productList.forEach(product -> {
            if (product instanceof Book) {
                bookList.add((Book) product);
            } else if (product instanceof Clothes) {
                clothesList.add((Clothes) product);
            } else if (product instanceof MobilePhone) {
                mobilePhoneList.add((MobilePhone) product);
            }
        });

        model.addAttribute("bookList", bookList);
        model.addAttribute("clothesList", clothesList);
        model.addAttribute("mobilePhoneList", mobilePhoneList);

        return "index";
    }

    @GetMapping("/cart")
    public String goPageShoppingCart(Model model) {
        Cart cart = cartService.getCart();
        model.addAttribute("cart", cart);
        model.addAttribute("page", "Shopping Cart");
        return "shopping-cart";
    }

    @GetMapping("/checkout")
    public String goPageCheckout(Model model) {
        Cart cart = cartService.getCart();
        model.addAttribute("cart", cart);
        model.addAttribute("page", "Checkout");
        return "checkout";
    }

    @GetMapping("/bills")
    public String goPageBills(Model model) {

        List<Order> orderList = orderService.getOrderList();
        model.addAttribute("orderList", orderList);

        model.addAttribute("page", "Bills");
        return "bills";
    }
    
    

}

package com.thuan.pttk.controller;

import com.thuan.pttk.entity.Product;
import com.thuan.pttk.entity.book.Book;
import com.thuan.pttk.entity.clothes.Clothes;
import com.thuan.pttk.entity.mobile.MobilePhone;
import com.thuan.pttk.service.ProductService;
import com.thuan.pttk.utils.ObjectUtil;
import jakarta.annotation.Nullable;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public String getPageShopAll(Model model, @Nullable @PathParam("filter") String filter) {
        model.addAttribute("page", "shop");
        List<Product> productList = productService.findAll();
        List<Book> bookList = new ArrayList<>();
        List<Clothes> clothesList = new ArrayList<>();
        List<MobilePhone> mobilePhoneList = new ArrayList<>();

        if (filter == null) {
            filter = "none";
        }
        switch (filter) {
            case "0":
                break;
            case "2":
                productList.sort((p1, p2) -> (int) (p2.getPrice() - p1.getPrice()));
                break;
            case "1":
                productList.sort((p1, p2) -> (int) (p1.getPrice() - p2.getPrice()));
                break;
            default:
                break;
        }

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
        model.addAttribute("page", "Shop");
        return "shop";
    }

    @GetMapping("/detail/{productId}")
    public String goPageDetail(@PathVariable long productId, Model model) throws IllegalAccessException {
        Product product = productService.findById(productId).get();

        Map<String, Object> fieldMap = null;

        if (product instanceof Book) {
            Book book = (Book) product;
            fieldMap = ObjectUtil.convertObjectToMap(book);
        } else if (product instanceof Clothes) {
            Clothes clothes = (Clothes) product;
            fieldMap = ObjectUtil.convertObjectToMap(clothes);
        } else if (product instanceof MobilePhone) {
            MobilePhone mobile = (MobilePhone) product;
            fieldMap = ObjectUtil.convertObjectToMap(mobile);
        }
        model.addAttribute("addtionalInformation", fieldMap);
        model.addAttribute("product", product);
        model.addAttribute("page", "Shop Detail");
        return "detail";
    }

    @GetMapping("/{type}")
    public String goPageByType(@PathVariable String type, Model model, @PathParam("filter") String filter) {
        List<Product> productList = new ArrayList<>();
        switch (type) {
            case "bookList":
                productList = productService.getAllBookProduct();
                break;
            case "clothesList":
                productList = productService.getAllClothesProduct();
                break;
            case "mobilePhoneList":
                productList = productService.getAllMobilePhoneProduct();
                break;

            default:
                break;
        }
        if (filter == null) {
            filter = "none";
        }
        switch (filter) {
            case "0":
                break;
            case "2":
                productList.sort((p1, p2) -> (int) (p2.getPrice() - p1.getPrice()));
                break;
            case "1":
                productList.sort((p1, p2) -> (int) (p1.getPrice() - p2.getPrice()));
                break;
            default:
                break;
        }
        model.addAttribute(type, productList);
        return "shop";
    }

}

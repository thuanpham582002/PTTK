package com.thuan.pttk.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thuan.pttk.entity.Product;
import com.thuan.pttk.entity.book.Book;
import com.thuan.pttk.entity.clothes.Clothes;
import com.thuan.pttk.entity.electronic.Computer;
import com.thuan.pttk.entity.electronic.Electronic;
import com.thuan.pttk.entity.electronic.Mobile;
import com.thuan.pttk.entity.shoes.ForMan;
import com.thuan.pttk.entity.shoes.ForWoman;
import com.thuan.pttk.entity.shoes.Shoes;
import com.thuan.pttk.service.ProductService;
import com.thuan.pttk.utils.ObjectUtil;

@Controller
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public String getPageShopAll(Model model) {
        model.addAttribute("page", "shop");
        List<Product> productList = productService.findAll();
        List<Book> bookList = new ArrayList<>();
        List<Clothes> clothesList = new ArrayList<>();
        List<Electronic> electronicList = new ArrayList<>();
        List<Shoes> shoesList = new ArrayList<>();

        productList.forEach(product -> {
            if (product instanceof Book) {
                bookList.add((Book) product);
            } else if (product instanceof Clothes) {
                clothesList.add((Clothes) product);
            } else if (product instanceof Electronic) {
                electronicList.add((Electronic) product);
            } else if (product instanceof Shoes) {
                shoesList.add((Shoes) product);
            }
        });

        model.addAttribute("bookList", bookList);
        model.addAttribute("clothesList", clothesList);
        model.addAttribute("electronicList", electronicList);
        model.addAttribute("shoesList", shoesList);
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
        } else if (product instanceof Computer) {
            Computer computer = (Computer) product;
            fieldMap = ObjectUtil.convertObjectToMap(computer);
        } else if (product instanceof Mobile) {
            Mobile mobile = (Mobile) product;
            fieldMap = ObjectUtil.convertObjectToMap(mobile);
        } else if (product instanceof ForMan) {
            ForMan forMan = (ForMan) product;
            fieldMap = ObjectUtil.convertObjectToMap(forMan);
        } else if (product instanceof ForWoman) {
            ForWoman forWoman = (ForWoman) product;
            fieldMap = ObjectUtil.convertObjectToMap(forWoman);
        }

        model.addAttribute("addtionalInformation", fieldMap);
        model.addAttribute("product", product);
        model.addAttribute("page", "Shop Detail");
        return "detail";
    }

    @GetMapping("/{type}")
    public String goPageByType(@PathVariable String type, Model model) {
        switch (type) {
            case "bookList":
                List<Product> bookList = productService.getAllBookProduct();
                model.addAttribute(type, bookList);
                break;
            case "clothesList":
                List<Product> clothesList = productService.getAllClothesProduct();
                model.addAttribute(type, clothesList);
                break;
            case "shoesList":
                List<Product> shoesList = productService.getAllShoesProduct();
                model.addAttribute(type, shoesList);
                break;
            case "electronicList":
                List<Product> electronicList = productService.getAllElectronicProduct();
                model.addAttribute(type, electronicList);
                break;

            default:
                break;
        }
        return "shop";
    }

}

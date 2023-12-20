package com.thuan.pttk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thuan.pttk.dto.OrderDto;
import com.thuan.pttk.service.OrderService;

@RestController
@RequestMapping("/checkout")
@CrossOrigin("*")
public class CheckoutRestController {
    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<Void> createCheckout(@RequestBody OrderDto orderDto) {
        boolean ok = orderService.createOrder(orderDto);
        System.out.println("Tao order thanh cong: " + ok);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable long orderId) {
        OrderDto orderDto = orderService.getOrderDtoById(orderId);
        return new ResponseEntity<OrderDto>(orderDto, HttpStatus.OK);
    }

}

package com.thuan.pttk.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.thuan.pttk.repository.CartRepository;
import com.thuan.pttk.repository.CustomerRepository;
import com.thuan.pttk.repository.OrderRepository;
import com.thuan.pttk.security.UserDetailsCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.thuan.pttk.dto.OrderDto;
import com.thuan.pttk.dto.OrderItemDto;
import com.thuan.pttk.entity.cart.Cart;
import com.thuan.pttk.entity.cart.CartItem;
import com.thuan.pttk.entity.cutstomer.Customer;
import com.thuan.pttk.entity.order.Order;
import com.thuan.pttk.entity.order.OrderItem;
import com.thuan.pttk.entity.payment.Cash;
import com.thuan.pttk.entity.payment.Credit;
import com.thuan.pttk.entity.payment.Payment;
import com.thuan.pttk.service.CartService;
import com.thuan.pttk.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CartService cartService;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public boolean createOrder(OrderDto orderDto) {
        Cart cart = cartService.getCart();

        Order order = Order.builder().date(LocalDateTime.now()).receiverName(orderDto.getReceiverName())
                .receiverMobile(orderDto.getReceiverMobile()).receiverAddress(orderDto.getReceiverAddress())
                .customer(cart.getCustomer()).build();

        List<OrderItem> orderItemList = new ArrayList<OrderItem>();

        List<CartItem> cartItemList = cart.getCartItemList();

        cartItemList.forEach(ci -> {
            orderItemList.add(
                    OrderItem.builder().order(order).product(ci.getProduct()).quantity(ci.getQuantity()).build());
        });

        cartItemList.clear();
        cart.setCartItemList(cartItemList);
        cartRepository.save(cart);

        order.setOrderItemList(orderItemList);

        Payment payment = null;

        if (orderDto.getPaymentType() == 1) {
            payment = Credit.builder().creditId(orderDto.getCreditId()).build();
        } else {
            payment = Cash.builder().cashTendered(order.getTotalPrice() + "").build();
        }
        order.setPayment(payment);
        orderRepository.save(order);
        return true;
    }

    @Override
    public List<Order> getOrderList() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        UserDetailsCustom user = (UserDetailsCustom) authentication.getPrincipal();
        Customer customer = customerRepository.findByUser(user.getUser()).get();
        List<Order> orderList = customer.getOrderList();
        for (Order order : orderList) {
            System.out.println(order.getReceiverAddress());
        }
        return orderList;
    }

    @Override
    public OrderDto getOrderDtoById(long orderId) {
        Order order = orderRepository.findById(orderId).get();
        List<OrderItemDto> orderItemDtoList = new ArrayList<OrderItemDto>();
        order.getOrderItemList().forEach(oi -> {
            orderItemDtoList.add(OrderItemDto.builder().product(oi.getProduct()).quantity(oi.getQuantity()).build());
        });
        OrderDto orderDto = OrderDto.builder().orderItemDtoList(orderItemDtoList).receiverName(order.getReceiverName())
                .receiverMobile(order.getReceiverMobile()).receiverAddress(order.getReceiverAddress())
                .date(order.getDate()).totalPrice(order.getTotalPrice())
                .build();
        return orderDto;
    }

}

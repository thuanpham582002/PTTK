package com.thuan.pttk.service;

import java.util.List;

import com.thuan.pttk.dto.OrderDto;
import com.thuan.pttk.entity.order.Order;

public interface OrderService {

    boolean createOrder(OrderDto orderDto);

    List<Order> getOrderList();

    OrderDto getOrderDtoById(long orderId);

}

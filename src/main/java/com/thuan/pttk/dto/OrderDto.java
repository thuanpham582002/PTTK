package com.thuan.pttk.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderDto {
    private String receiverName;
    private String receiverMobile;
    private String receiverAddress;
    private int paymentType;
    private String creditId;
    private List<OrderItemDto> orderItemDtoList;
    private float totalPrice;
    private LocalDateTime date;
}

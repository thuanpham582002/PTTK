package com.thuan.pttk.dto;

import com.thuan.pttk.entity.Product;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderItemDto {
    private Product product;
    private int quantity;
}

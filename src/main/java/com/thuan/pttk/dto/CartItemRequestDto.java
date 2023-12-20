package com.thuan.pttk.dto;

import lombok.Data;

@Data
public class CartItemRequestDto {
    private long productId;
    private int quantity;
    private long cartItemId;
}

package com.community.tradeservice.dto;

import com.community.tradeservice.model.OrderStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderDTO {
    private Long id;

    @NotNull(message = "商品ID不能为空")
    private Long itemId;

    @NotNull(message = "买家ID不能为空")
    private Long buyerId;

    private Double finalPrice;
    private OrderStatus status = OrderStatus.PENDING;
    private String shippingAddress;
    private String contactInfo;
    private String notes;
}
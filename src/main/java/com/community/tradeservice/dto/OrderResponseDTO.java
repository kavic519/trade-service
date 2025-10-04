package com.community.tradeservice.dto;

import com.community.tradeservice.model.OrderStatus;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class OrderResponseDTO {
    private Long id;
    private Long itemId;
    private String itemTitle;
    private Long buyerId;
    private String buyerUsername;
    private Double finalPrice;
    private OrderStatus status;
    private String shippingAddress;
    private String contactInfo;
    private String notes;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
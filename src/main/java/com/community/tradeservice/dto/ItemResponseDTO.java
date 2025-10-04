package com.community.tradeservice.dto;

import com.community.tradeservice.model.ItemStatus;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ItemResponseDTO {
    private Long id;
    private String title;
    private String description;
    private Double price;
    private String category;
    private String condition;
    private String location;
    private String images;
    private ItemStatus status;
    private Long sellerId;
    private String sellerUsername;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
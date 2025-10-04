package com.community.tradeservice.dto;

import com.community.tradeservice.model.ItemStatus;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ItemDTO {
    private Long id;

    @NotBlank(message = "商品标题不能为空")
    private String title;

    @NotBlank(message = "商品描述不能为空")
    private String description;

    @NotNull(message = "价格不能为空")
    @DecimalMin(value = "0.0", message = "价格必须大于0")
    private Double price;

    private String category;
    private String condition;
    private String location;
    private String images;
    private ItemStatus status = ItemStatus.AVAILABLE;
    private Long sellerId;
}
package com.community.tradeservice.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class UserResponseDTO {
    private Long id;
    private String username;
    private String email;
    private String phone;
    private String avatar;
    private Double rating;
    private Integer tradeCount;
    private LocalDateTime createTime;

    // 不包含 items 和 orders 列表，避免循环引用
}
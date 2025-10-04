package com.community.tradeservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "商品标题不能为空")
    private String title;

    @NotBlank(message = "商品描述不能为空")
    @Column(columnDefinition = "TEXT")
    private String description;

    @NotNull(message = "价格不能为空")
    @DecimalMin(value = "0.0", message = "价格必须大于0")
    private Double price;

    private String category; // 商品分类
    private String condition; // 商品状态：全新、二手等
    private String location; // 位置
    private String images; // 图片URL，多个用逗号分隔

    @Enumerated(EnumType.STRING)
    private ItemStatus status = ItemStatus.AVAILABLE; // 商品状态

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id")
    @JsonIgnoreProperties({"items", "orders"}) // 忽略用户的 items 和 orders
    private User seller;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    @JsonIgnore // 打破循环引用
    private List<Order> orders = new ArrayList<>();

    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    @PrePersist
    protected void onCreate() {
        createTime = LocalDateTime.now();
        updateTime = LocalDateTime.now();
    }


    @PreUpdate
    protected void onUpdate() {
        updateTime = LocalDateTime.now();


    }



    // 移除内部枚举定义
}
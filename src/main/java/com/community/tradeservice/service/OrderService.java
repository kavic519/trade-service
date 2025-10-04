package com.community.tradeservice.service;

import com.community.tradeservice.dto.OrderDTO;
import com.community.tradeservice.model.*;
import com.community.tradeservice.repository.ItemRepository;
import com.community.tradeservice.repository.OrderRepository;
import com.community.tradeservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public List<Order> getOrdersByBuyer(Long buyerId) {
        return orderRepository.findByBuyerId(buyerId);
    }

    public List<Order> getOrdersBySeller(Long sellerId) {
        return orderRepository.findByItemSellerId(sellerId);
    }

    public Order createOrder(OrderDTO orderDTO) {
        Item item = itemRepository.findById(orderDTO.getItemId())
                .orElseThrow(() -> new RuntimeException("商品不存在"));

        User buyer = userRepository.findById(orderDTO.getBuyerId())
                .orElseThrow(() -> new RuntimeException("买家不存在"));

        if (item.getStatus() != ItemStatus.AVAILABLE) {
            throw new RuntimeException("商品不可用");
        }

        // 检查是否是自己购买自己的商品
        if (item.getSeller().getId().equals(buyer.getId())) {
            throw new RuntimeException("不能购买自己的商品");
        }

        Order order = new Order();
        order.setItem(item);
        order.setBuyer(buyer);
        order.setFinalPrice(orderDTO.getFinalPrice() != null ?
                orderDTO.getFinalPrice() : item.getPrice());
        order.setStatus(OrderStatus.PENDING);
        order.setShippingAddress(orderDTO.getShippingAddress());
        order.setContactInfo(orderDTO.getContactInfo());
        order.setNotes(orderDTO.getNotes());

        // 更新商品状态为已预订
        item.setStatus(ItemStatus.RESERVED);
        itemRepository.save(item);

        return orderRepository.save(order);
    }

    public Order updateOrderStatus(Long id, OrderStatus status) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("订单不存在"));

        OrderStatus oldStatus = order.getStatus();
        order.setStatus(status);

        Order updatedOrder = orderRepository.save(order);

        // 如果订单完成，更新商品状态和用户评分
        if (status == OrderStatus.COMPLETED && oldStatus != OrderStatus.COMPLETED) {
            Item item = order.getItem();
            item.setStatus(ItemStatus.SOLD);
            itemRepository.save(item);

            // 更新卖家评分（这里简化处理，实际应该根据买家评价）
            userService.updateUserRating(item.getSeller().getId(), 5.0);
        }

        return updatedOrder;
    }

    public void cancelOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("订单不存在"));

        // 更新商品状态为可用
        Item item = order.getItem();
        item.setStatus(ItemStatus.AVAILABLE);
        itemRepository.save(item);

        // 删除订单或标记为取消
        order.setStatus(OrderStatus.CANCELLED);
        orderRepository.save(order);
    }
}
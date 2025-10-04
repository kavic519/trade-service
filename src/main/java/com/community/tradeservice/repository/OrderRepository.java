package com.community.tradeservice.repository;

import com.community.tradeservice.model.Order;
import com.community.tradeservice.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByBuyerId(Long buyerId);
    List<Order> findByItemSellerId(Long sellerId);

    @Query("SELECT o FROM Order o WHERE o.item.id = :itemId")
    List<Order> findByItemId(@Param("itemId") Long itemId);

    List<Order> findByStatus(OrderStatus status);

    @Query("SELECT COUNT(o) FROM Order o WHERE o.buyer.id = :buyerId AND o.status = :status")
    Long countByBuyerIdAndStatus(@Param("buyerId") Long buyerId,
                                 @Param("status") OrderStatus status);
}
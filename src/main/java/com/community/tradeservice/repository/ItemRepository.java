package com.community.tradeservice.repository;

import com.community.tradeservice.model.Item;
import com.community.tradeservice.model.ItemStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findBySellerIdAndStatus(Long sellerId, ItemStatus status);
    List<Item> findByStatus(ItemStatus status);
    List<Item> findByCategoryAndStatus(String category, ItemStatus status);

    @Query("SELECT i FROM Item i WHERE " +
            "(LOWER(i.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(i.description) LIKE LOWER(CONCAT('%', :keyword, '%'))) AND " +
            "i.status = :status")
    List<Item> searchByKeywordAndStatus(@Param("keyword") String keyword,
                                        @Param("status") ItemStatus status);

    @Query("SELECT i FROM Item i WHERE i.seller.id = :sellerId")
    List<Item> findBySellerId(@Param("sellerId") Long sellerId);
}
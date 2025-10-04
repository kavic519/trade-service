package com.community.tradeservice.service;

import com.community.tradeservice.dto.ItemDTO;
import com.community.tradeservice.model.Item;
import com.community.tradeservice.model.ItemStatus;  // 现在可以正常导入
import com.community.tradeservice.model.User;
import com.community.tradeservice.repository.ItemRepository;
import com.community.tradeservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    public List<Item> getAllItems() {
        return itemRepository.findByStatus(ItemStatus.AVAILABLE);
    }

    public Optional<Item> getItemById(Long id) {
        return itemRepository.findById(id);
    }

    public List<Item> getItemsByCategory(String category) {
        return itemRepository.findByCategoryAndStatus(category, ItemStatus.AVAILABLE);
    }

    public List<Item> searchItems(String keyword) {
        return itemRepository.searchByKeywordAndStatus(keyword, ItemStatus.AVAILABLE);
    }

    public List<Item> getItemsBySeller(Long sellerId) {
        return itemRepository.findBySellerId(sellerId);
    }

    public Item createItem(ItemDTO itemDTO) {
        User seller = userRepository.findById(itemDTO.getSellerId())
                .orElseThrow(() -> new RuntimeException("卖家不存在"));

        Item item = new Item();
        item.setTitle(itemDTO.getTitle());
        item.setDescription(itemDTO.getDescription());
        item.setPrice(itemDTO.getPrice());
        item.setCategory(itemDTO.getCategory());
        item.setCondition(itemDTO.getCondition());
        item.setLocation(itemDTO.getLocation());
        item.setImages(itemDTO.getImages());
        item.setStatus(itemDTO.getStatus());
        item.setSeller(seller);

        return itemRepository.save(item);
    }

    public Item updateItem(Long id, ItemDTO itemDTO) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("商品不存在"));

        if (itemDTO.getTitle() != null) {
            item.setTitle(itemDTO.getTitle());
        }
        if (itemDTO.getDescription() != null) {
            item.setDescription(itemDTO.getDescription());
        }
        if (itemDTO.getPrice() != null) {
            item.setPrice(itemDTO.getPrice());
        }
        if (itemDTO.getCategory() != null) {
            item.setCategory(itemDTO.getCategory());
        }
        if (itemDTO.getCondition() != null) {
            item.setCondition(itemDTO.getCondition());
        }
        if (itemDTO.getLocation() != null) {
            item.setLocation(itemDTO.getLocation());
        }
        if (itemDTO.getImages() != null) {
            item.setImages(itemDTO.getImages());
        }
        if (itemDTO.getStatus() != null) {
            item.setStatus(itemDTO.getStatus());
        }

        return itemRepository.save(item);
    }

    public void deleteItem(Long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("商品不存在"));

        item.setStatus(ItemStatus.DELETED);
        itemRepository.save(item);
    }

    public Item markAsSold(Long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("商品不存在"));

        item.setStatus(ItemStatus.SOLD);
        return itemRepository.save(item);
    }
}
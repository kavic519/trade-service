package com.community.tradeservice.controller;

import com.community.tradeservice.dto.ItemDTO;
import com.community.tradeservice.dto.ItemResponseDTO;
import com.community.tradeservice.model.Item;
import com.community.tradeservice.service.ItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    // 转换 Item 到 ItemResponseDTO
    private ItemResponseDTO convertToDTO(Item item) {
        ItemResponseDTO dto = new ItemResponseDTO();
        dto.setId(item.getId());
        dto.setTitle(item.getTitle());
        dto.setDescription(item.getDescription());
        dto.setPrice(item.getPrice());
        dto.setCategory(item.getCategory());
        dto.setCondition(item.getCondition());
        dto.setLocation(item.getLocation());
        dto.setImages(item.getImages());
        dto.setStatus(item.getStatus());
        dto.setSellerId(item.getSeller().getId());
        dto.setSellerUsername(item.getSeller().getUsername());
        dto.setCreateTime(item.getCreateTime());
        dto.setUpdateTime(item.getUpdateTime());
        return dto;
    }

    @GetMapping
    public ResponseEntity<List<ItemResponseDTO>> getAllItems() {
        List<ItemResponseDTO> items = itemService.getAllItems().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(items);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemResponseDTO> getItemById(@PathVariable Long id) {
        return itemService.getItemById(id)
                .map(this::convertToDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<ItemResponseDTO>> getItemsByCategory(@PathVariable String category) {
        List<ItemResponseDTO> items = itemService.getItemsByCategory(category).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(items);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ItemResponseDTO>> searchItems(@RequestParam String keyword) {
        List<ItemResponseDTO> items = itemService.searchItems(keyword).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(items);
    }

    @GetMapping("/seller/{sellerId}")
    public ResponseEntity<List<ItemResponseDTO>> getItemsBySeller(@PathVariable Long sellerId) {
        List<ItemResponseDTO> items = itemService.getItemsBySeller(sellerId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(items);
    }

    @PostMapping
    public ResponseEntity<ItemResponseDTO> createItem(@Valid @RequestBody ItemDTO itemDTO) {
        try {
            Item item = itemService.createItem(itemDTO);
            return ResponseEntity.ok(convertToDTO(item));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemResponseDTO> updateItem(@PathVariable Long id,
                                                      @Valid @RequestBody ItemDTO itemDTO) {
        try {
            Item item = itemService.updateItem(id, itemDTO);
            return ResponseEntity.ok(convertToDTO(item));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        try {
            itemService.deleteItem(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}/sold")
    public ResponseEntity<ItemResponseDTO> markAsSold(@PathVariable Long id) {
        try {
            Item item = itemService.markAsSold(id);
            return ResponseEntity.ok(convertToDTO(item));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
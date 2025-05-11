package com.expiryalert.service;

import com.expiryalert.model.Item;
import com.expiryalert.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class ItemService {
    
    @Autowired
    private ItemRepository itemRepository;

    public List<Item> getAllItems() {
        return itemRepository.findByWastedFalseOrderByExpiryAsc();
    }

    public List<Item> getWastedItems() {
        return itemRepository.findByWastedOrderByExpiryAsc(true);
    }

    public Item createItem(Item item) {
        return itemRepository.save(item);
    }

    public Item updateItem(Long id, Item itemDetails) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found"));
        
        item.setName(itemDetails.getName());
        item.setExpiry(itemDetails.getExpiry());
        
        return itemRepository.save(item);
    }

    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }

    public Item markAsWasted(Long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found"));
        
        item.setWasted(true);
        return itemRepository.save(item);
    }

    public String getItemStatus(LocalDate expiry) {
        LocalDate today = LocalDate.now();
        long diff = java.time.temporal.ChronoUnit.DAYS.between(today, expiry);
        
        if (diff < 0) return "expired";
        if (diff <= 3) return "near";
        return "safe";
    }
} 
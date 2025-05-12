package com.expiryalert.repository;

import com.expiryalert.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByExpiryBeforeAndWastedFalse(LocalDate date);
    List<Item> findByWastedTrue();
    List<Item> findByLocationId(Long locationId);
} 
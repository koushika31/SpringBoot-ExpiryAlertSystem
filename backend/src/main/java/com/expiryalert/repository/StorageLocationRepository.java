package com.expiryalert.repository;

import com.expiryalert.model.StorageLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StorageLocationRepository extends JpaRepository<StorageLocation, Long> {
    List<StorageLocation> findByNameContainingIgnoreCase(String name);
} 
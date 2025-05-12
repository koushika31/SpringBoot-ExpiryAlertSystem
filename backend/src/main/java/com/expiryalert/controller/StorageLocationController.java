package com.expiryalert.controller;

import com.expiryalert.model.StorageLocation;
import com.expiryalert.repository.StorageLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/locations")
@CrossOrigin(origins = "http://localhost:3000")
public class StorageLocationController {
    
    @Autowired
    private StorageLocationRepository locationRepository;
    
    @GetMapping
    public List<StorageLocation> getAllLocations() {
        return locationRepository.findAll();
    }
    
    @PostMapping
    public StorageLocation createLocation(@RequestBody StorageLocation location) {
        return locationRepository.save(location);
    }
    
    @PutMapping("/{id}")
    public StorageLocation updateLocation(@PathVariable Long id, @RequestBody StorageLocation location) {
        location.setId(id);
        return locationRepository.save(location);
    }
    
    @DeleteMapping("/{id}")
    public void deleteLocation(@PathVariable Long id) {
        locationRepository.deleteById(id);
    }
    
    @GetMapping("/search")
    public List<StorageLocation> searchLocations(@RequestParam String name) {
        return locationRepository.findByNameContainingIgnoreCase(name);
    }
} 
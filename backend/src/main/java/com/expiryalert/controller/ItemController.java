package com.expiryalert.controller;

import com.expiryalert.model.Item;
import com.expiryalert.service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/items")
@CrossOrigin(origins = "http://localhost:3000")
@Tag(name = "Item Management", description = "APIs for managing items and their expiry dates")
public class ItemController {
    
    @Autowired
    private ItemService itemService;
    
    @Operation(summary = "Get all items", description = "Retrieves a list of all items in the system")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved all items")
    @GetMapping
    public List<Item> getAllItems() {
        return itemService.getAllItems();
    }
    
    @Operation(summary = "Create new item", description = "Creates a new item with the provided details")
    @ApiResponse(responseCode = "200", description = "Item created successfully")
    @PostMapping
    public Item createItem(@RequestBody Item item) {
        return itemService.saveItem(item);
    }
    
    @Operation(summary = "Update item", description = "Updates an existing item by ID")
    @ApiResponse(responseCode = "200", description = "Item updated successfully")
    @PutMapping("/{id}")
    public Item updateItem(
            @Parameter(description = "ID of the item to update") @PathVariable Long id,
            @RequestBody Item item) {
        item.setId(id);
        return itemService.saveItem(item);
    }
    
    @Operation(summary = "Delete item", description = "Deletes an item by ID")
    @ApiResponse(responseCode = "200", description = "Item deleted successfully")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteItem(
            @Parameter(description = "ID of the item to delete") @PathVariable Long id) {
        itemService.deleteItem(id);
        return ResponseEntity.ok().build();
    }
    
    @Operation(summary = "Mark item as wasted", description = "Marks an item as wasted and records the waste date")
    @ApiResponse(responseCode = "200", description = "Item marked as wasted successfully")
    @PostMapping("/{id}/waste")
    public Item markAsWasted(
            @Parameter(description = "ID of the item to mark as wasted") @PathVariable Long id) {
        return itemService.markAsWasted(id);
    }
    
    @Operation(summary = "Get wasted items", description = "Retrieves a list of all wasted items")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved wasted items")
    @GetMapping("/wasted")
    public List<Item> getWastedItems() {
        return itemService.getWastedItems();
    }
    
    @Operation(summary = "Get items by location", description = "Retrieves all items in a specific storage location")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved items by location")
    @GetMapping("/location/{locationId}")
    public List<Item> getItemsByLocation(
            @Parameter(description = "ID of the storage location") @PathVariable Long locationId) {
        return itemService.getItemsByLocation(locationId);
    }
} 
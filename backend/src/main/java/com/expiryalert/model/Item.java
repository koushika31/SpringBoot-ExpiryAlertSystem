package com.expiryalert.model;

import lombok.Data;
import jakarta.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private LocalDate expiry;
    
    @ManyToOne
    @JoinColumn(name = "location_id")
    private StorageLocation location;
    
    private boolean wasted;
    
    private LocalDate wastedDate;
} 
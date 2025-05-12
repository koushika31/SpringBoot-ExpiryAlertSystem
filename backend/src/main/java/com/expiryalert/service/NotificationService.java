package com.expiryalert.service;

import com.expiryalert.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class NotificationService {
    
    @Autowired
    private JavaMailSender emailSender;
    
    @Autowired
    private ItemService itemService;
    
    @Scheduled(cron = "0 * * * * ?")
    public void checkExpiringItems() {
        System.out.println("Checking for expiring items...");
        LocalDate threeDaysFromNow = LocalDate.now().plusDays(3);
        List<Item> expiringItems = itemService.findItemsExpiringBefore(threeDaysFromNow);
        
        for (Item item : expiringItems) {
            sendExpiryNotification(item);
        }
    }
    
    private void sendExpiryNotification(Item item) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("rmkoushika3115@gmail.com");
        message.setSubject("⚠️ EXPIRY ALERT: " + item.getName());
        
        // Calculate days until expiry
        long daysUntilExpiry = ChronoUnit.DAYS.between(LocalDate.now(), item.getExpiry());
        String urgencyLevel = daysUntilExpiry == 0 ? "URGENT" : "WARNING";
        
        // Create alert-style message
        String alertMessage = String.format("""
            ⚠️ EXPIRY ALERT ⚠️
            ===================
            
            Product: %s
            Expiry Date: %s
            Days Remaining: %d
            Status: %s
            
            ACTION REQUIRED:
            Please check this item immediately!
            
            Location: %s
            
            This is an automated alert from your Expiry Alert System.
            ===================
            """,
            item.getName(),
            item.getExpiry(),
            daysUntilExpiry,
            urgencyLevel,
            item.getLocation() != null ? item.getLocation().getName() : "Not specified"
        );
        
        message.setText(alertMessage);
        emailSender.send(message);
        System.out.println("Alert sent for item: " + item.getName());
    }
} 
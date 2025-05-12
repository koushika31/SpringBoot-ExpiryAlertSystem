package com.expiryalert.controller;

import com.expiryalert.model.Item;
import com.expiryalert.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    private JavaMailSender emailSender;
    
    @Autowired
    private ItemService itemService;

    @GetMapping("/send-email")
    public String sendTestEmail() {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo("rmkoushika3115@gmail.com");
            message.setSubject("Test Email from Expiry Alert");
            message.setText("This is a test email from your Expiry Alert application. If you receive this, the email configuration is working correctly!");
            
            emailSender.send(message);
            return "Test email sent successfully!";
        } catch (Exception e) {
            return "Error sending email: " + e.getMessage();
        }
    }
    
    @PostMapping("/add-item")
    public String addCustomItem(@RequestParam String name, @RequestParam String expiryDate) {
        try {
            Item item = new Item();
            item.setName(name);
            item.setExpiry(LocalDate.parse(expiryDate));
            itemService.saveItem(item);
            
            // Calculate days until expiry
            long daysUntilExpiry = java.time.temporal.ChronoUnit.DAYS.between(LocalDate.now(), item.getExpiry());
            
            String response = String.format("""
                Item added successfully!
                Name: %s
                Expiry Date: %s
                Days until expiry: %d
                
                %s
                """,
                name,
                expiryDate,
                daysUntilExpiry,
                daysUntilExpiry <= 3 ? "You will receive an alert for this item!" : "No alert will be sent (expires in more than 3 days)"
            );
            
            return response;
        } catch (Exception e) {
            return "Error adding item: " + e.getMessage();
        }
    }
    
    @PostMapping("/add-test-items")
    public String addTestItems() {
        try {
            // Items expiring today (URGENT)
            Item item1 = new Item();
            item1.setName("Milk");
            item1.setExpiry(LocalDate.now());
            itemService.saveItem(item1);
            
            Item item2 = new Item();
            item2.setName("Yogurt");
            item2.setExpiry(LocalDate.now());
            itemService.saveItem(item2);
            
            // Items expiring in 2 days (WARNING)
            Item item3 = new Item();
            item3.setName("Bread");
            item3.setExpiry(LocalDate.now().plusDays(2));
            itemService.saveItem(item3);
            
            Item item4 = new Item();
            item4.setName("Eggs");
            item4.setExpiry(LocalDate.now().plusDays(2));
            itemService.saveItem(item4);
            
            // Items expiring in 5 days (No alert)
            Item item5 = new Item();
            item5.setName("Cheese");
            item5.setExpiry(LocalDate.now().plusDays(5));
            itemService.saveItem(item5);
            
            Item item6 = new Item();
            item6.setName("Butter");
            item6.setExpiry(LocalDate.now().plusDays(5));
            itemService.saveItem(item6);
            
            return "Test items added successfully! You should receive alerts for:\n" +
                   "- Milk (expiring today)\n" +
                   "- Yogurt (expiring today)\n" +
                   "- Bread (expiring in 2 days)\n" +
                   "- Eggs (expiring in 2 days)\n\n" +
                   "No alerts for:\n" +
                   "- Cheese (expiring in 5 days)\n" +
                   "- Butter (expiring in 5 days)";
        } catch (Exception e) {
            return "Error adding test items: " + e.getMessage();
        }
    }
} 
package com.bhanu.ecommerce_backend.Service;

import com.bhanu.ecommerce_backend.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendOrderConfirmation(String toEmail, Order order) {
        String subject = "Order Confirmation - E-Commerce App";
        String message = "Hi " + order.getUser().getUsername() + ",\n\n"
                + "Your order has been placed successfully.\n"
                + "Expected Delivery: " + order.getExpectedDelivery() + "\n"
                + "Order Total: Rs." + order.getItems().stream()
                .mapToInt(i -> i.getPrice() * i.getQuantity())
                .sum()
                + "\n\nThanks for shopping with us!";

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(toEmail);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        mailSender.send(mailMessage);
    }
}


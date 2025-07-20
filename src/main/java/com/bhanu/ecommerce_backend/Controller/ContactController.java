package com.bhanu.ecommerce_backend.Controller;

import com.bhanu.ecommerce_backend.model.ContactRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/contact")
public class ContactController {

    @Autowired
    private JavaMailSender mailSender;

    @PostMapping("/send")
    public ResponseEntity<String> sendContactEmail(@RequestBody ContactRequest contact) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo("bhanucse16@gmail.com");  // admin email
            message.setSubject("New Contact Message from: " + contact.getName());
            message.setText("Email: " + contact.getEmail() + "\n\nMessage:\n" + contact.getMessage());
            mailSender.send(message);

            SimpleMailMessage userReply = new SimpleMailMessage();
            userReply.setTo(contact.getEmail());
            userReply.setSubject("We received your message!");
            userReply.setText("Hi " + contact.getName() + ",\n\n"
                    + "Thanks for contacting us. We’ve received your message and will respond shortly.\n\n"
                    + "Here's a copy of your message:\n" + contact.getMessage() + "\n\n"
                    + "Regards,\nMyShop(@Bhanu) Team");
            mailSender.send(userReply);

            return ResponseEntity.ok("✅ Message sent successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("❌ Failed to send message.");
        }
    }
}

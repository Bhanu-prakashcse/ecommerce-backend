package com.bhanu.ecommerce_backend.Controller;

import com.bhanu.ecommerce_backend.Service.UserService;
import com.bhanu.ecommerce_backend.dto.UpdatePhoneNumberDTO;
import com.bhanu.ecommerce_backend.model.User;
import com.bhanu.ecommerce_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.awt.image.RescaleOp;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<User> getUserProfile(Authentication authentication) {
        String username = authentication.getName();
        User user=userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        return ResponseEntity.ok(user);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/update-phone")
    public ResponseEntity<String> updatePhone(@RequestBody UpdatePhoneNumberDTO dto){
        String result=userService.updatePhoneNumber(dto.getUsername(),dto.getPhoneNumber());
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/admin/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        userRepository.deleteById(id);
        return ResponseEntity.ok("User deleted successfully");
    }
}

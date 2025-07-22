//package com.bhanu.ecommerce_backend.Service;
//
//import com.bhanu.ecommerce_backend.model.User;
//import com.bhanu.ecommerce_backend.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UserService {
//    @Autowired
//    private UserRepository userRepository;
//
//    public String updatePhoneNumber(String username, String PhoneNumber) {
//        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
//        user.setPhoneNumber(PhoneNumber);
//        userRepository.save(user);
//        return "Phone number updated successfully for user: " + username;
//    }
//}

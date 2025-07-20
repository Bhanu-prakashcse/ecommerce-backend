package com.bhanu.ecommerce_backend.Controller;

import com.bhanu.ecommerce_backend.Service.AuthService;
import com.bhanu.ecommerce_backend.dto.AuthRequest;
import com.bhanu.ecommerce_backend.dto.AuthResponse;
import com.bhanu.ecommerce_backend.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor

public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody AuthRequest request, @RequestParam Role role){
        return  ResponseEntity.ok(authService.register(request,role));
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody AuthRequest request){
        AuthResponse response = authService.authenticate(request);
        Map<String, String> mp=new HashMap<>();
        mp.put("token",response.getToken());
        mp.put("role", response.getRole().name());
        return ResponseEntity.ok(mp);
    }
}

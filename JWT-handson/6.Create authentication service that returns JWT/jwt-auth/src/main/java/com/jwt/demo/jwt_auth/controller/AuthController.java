package com.jwt.demo.jwt_auth.controller;

import com.jwt.demo.jwt_auth.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@RestController
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/authenticate")
    public ResponseEntity<?> authenticate(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        // Debug log: show incoming Authorization header
        System.out.println(">>> Authorization Header: " + authHeader);

        if (authHeader != null && authHeader.startsWith("Basic ")) {
            try {
                String base64Credentials = authHeader.substring("Basic ".length()).trim();
                byte[] decodedBytes = Base64.getDecoder().decode(base64Credentials);
                String credentials = new String(decodedBytes, StandardCharsets.UTF_8);

                // Debug log: decoded user:pwd string
                System.out.println(">>> Decoded credentials: " + credentials);

                String[] values = credentials.split(":", 2);
                if (values.length != 2) {
                    return ResponseEntity.badRequest().body("Invalid Authorization format");
                }

                String username = values[0];
                String password = values[1];

                // Debug log: show username and password
                System.out.println(">>> Username: " + username + ", Password: " + password);

                if ("user".equals(username) && "pwd".equals(password)) {
                    String token = jwtUtil.generateToken(username);
                    System.out.println(">>> JWT Token: " + token);  // Debug token
                    return ResponseEntity.ok().body("{\"token\":\"" + token + "\"}");
                } else {
                    return ResponseEntity.status(401).body("Invalid credentials");
                }

            } catch (IllegalArgumentException e) {
                return ResponseEntity.badRequest().body("Failed to decode credentials: " + e.getMessage());
            }
        }

        return ResponseEntity.status(400).body("Authorization header missing or invalid");
    }
}

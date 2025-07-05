package com.example.auth_service.controller;

import com.example.auth_service.dto.JwtClaims;
import com.example.auth_service.jwt.JwtUtil;
import com.example.auth_service.repository.UserRepository;
import com.example.auth_service.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthoritiesController {

    private final AuthService authService;

    private final JwtUtil jwtUtil;

    @GetMapping("/permission")
    public ResponseEntity<List<String>> getPermissionsByUserEmail(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
        System.out.println("/api/permission вызван");
        String token = authHeader.replace("Bearer ", "");
        JwtClaims jwtClaims = jwtUtil.parseToken(token);
        String email = jwtClaims.username();
        return ResponseEntity.ok(authService.getPermissionsByUserEmail(email));
    }
}

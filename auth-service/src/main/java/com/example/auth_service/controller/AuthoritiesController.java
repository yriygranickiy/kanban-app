package com.example.auth_service.controller;

import com.example.auth_service.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthoritiesController {

    private final AuthService authService;

    @GetMapping("/permission")
    public ResponseEntity<List<String>> getPermissionsByUserId(@RequestParam UUID id) {
        return ResponseEntity.ok(authService.getPermissionsByUserId(id));
    }
}

package com.example.auth_service.controller;

import com.example.auth_service.dto.JwtClaims;
import com.example.auth_service.dto.UserDTO;
import com.example.auth_service.dto.UserInfoDTO;
import com.example.auth_service.jwt.JwtUtil;
import com.example.auth_service.repository.UserRepository;
import com.example.auth_service.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthoritiesController {

    private final AuthService authService;

    private final JwtUtil jwtUtil;

    @GetMapping("/permission_and_id")
    public ResponseEntity<UserInfoDTO> getPermissionsByUserEmail(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
        System.out.println("/api/permission_and_id---------call");

        String token = authHeader.replace("Bearer ", "");
        JwtClaims jwtClaims = jwtUtil.parseToken(token);

        UUID user_id = jwtClaims.user_id();
        String email = jwtClaims.email();

        List<String> permission = authService.getPermissionsByUserEmail(email);

        return ResponseEntity.ok(new UserInfoDTO(user_id, email, permission));
    }
}

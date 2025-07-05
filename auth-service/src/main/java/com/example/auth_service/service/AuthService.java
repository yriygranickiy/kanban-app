package com.example.auth_service.service;

import com.example.auth_service.dto.*;
import com.example.auth_service.model.User;

import java.util.List;
import java.util.UUID;

public interface AuthService {

    UserDTO register(RegisterRequest registerRequest);

    TokenDTO login(LoginRequest loginRequest);

    List<String> getPermissionsByUserEmail(String email);
}

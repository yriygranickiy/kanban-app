package com.example.auth_service.service;

import com.example.auth_service.dto.AssignAuthorityRequest;
import com.example.auth_service.dto.LoginRequest;
import com.example.auth_service.dto.RegisterRequest;
import com.example.auth_service.model.User;

public interface AuthService {

    User register(RegisterRequest registerRequest);

    String login(LoginRequest loginRequest);

    void assignAuthorityToRole(AssignAuthorityRequest assignAuthorityRequest);
}

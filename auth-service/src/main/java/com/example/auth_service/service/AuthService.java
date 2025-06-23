package com.example.auth_service.service;

import com.example.auth_service.dto.*;
import com.example.auth_service.model.User;

public interface AuthService {

    UserDTO register(RegisterRequest registerRequest);

    TokenDTO login(LoginRequest loginRequest);

    void assignAuthorityToRole(AssignAuthorityRequest assignAuthorityRequest);
}

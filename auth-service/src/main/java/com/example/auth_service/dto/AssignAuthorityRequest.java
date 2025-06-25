package com.example.auth_service.dto;

public record AssignAuthorityRequest(
        String roleName,
        String authority
) {}

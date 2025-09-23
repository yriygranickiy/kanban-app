package com.kanban.task_service.dto.Board;


import jakarta.validation.constraints.NotBlank;

public record BoardRequestDto(
        @NotBlank(message = "Please provide a board name!")
        String name) {}

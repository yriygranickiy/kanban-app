package com.kanban.task_service.dto.Board;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BoardRequestDto(
        @NotBlank(message = "Please provide a board name!")
        String name) {}

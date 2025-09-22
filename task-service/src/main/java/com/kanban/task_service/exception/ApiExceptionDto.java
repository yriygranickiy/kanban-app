package com.kanban.task_service.exception;

import lombok.Builder;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;
import java.util.List;

@Builder
public record ApiExceptionDto(
        String message,
        List<String> errors,
        HttpStatus status,
        ZonedDateTime dateTime
) {
}

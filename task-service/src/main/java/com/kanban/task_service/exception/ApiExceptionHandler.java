package com.kanban.task_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;

@ControllerAdvice
public class ApiExceptionHandler {


//    @ExceptionHandler(value = {ApiRequestException.class})
//    protected ResponseEntity<Object> handleApiException(ApiRequestException exception) {
//
//        //1.Create payload containing exception details
//        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
//
//        ApiExceptionDto apiException = ApiExceptionDto.builder()
//                .message(exception.getMessage())
//                .status(badRequest)
//                .dateTime(ZonedDateTime.now(ZoneId.of("GMT+2")))
//                .build();
//
//        return ResponseEntity.status(badRequest)
//                            .body(apiException);
//    }

    // 🔥 Обработка валидации DTO (включая рекорды)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .toList();

        ApiExceptionDto apiException = ApiExceptionDto.builder()
                .message("Validation failed")
                .status(HttpStatus.BAD_REQUEST)
                .errors(errors)
                .dateTime(ZonedDateTime.now(ZoneId.of("GMT+2")))
                .build();

        return ResponseEntity.badRequest().body(apiException);
    }
}

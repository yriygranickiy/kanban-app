package com.kanban.task_service.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {ApiRequestException.class})
    protected ResponseEntity<Object> buildResponseEntity(ApiRequestException exception) {
        //1.Create payload containing exception details
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ApiExceptionDto apiExceptionDto = ApiExceptionDto.builder()
                .message(exception.getMessage())
                .status(badRequest)
                .dateTime(ZonedDateTime.now(ZoneId.of("GMT+2")))
                .build();

        return new ResponseEntity<>(apiExceptionDto, badRequest);
    }

}

package ru.practicum.explore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handlerCustomValidException(final CustomValidException e) {
        String status = String.valueOf(HttpStatus.BAD_REQUEST);
        String reason = "Incorrectly made request.";
        String message = e.getMessage();
        LocalDateTime time = LocalDateTime.now();
        return new ApiError(message, reason, status, time);
    }
}

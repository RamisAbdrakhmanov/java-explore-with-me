package ru.practicum.explore.exeption;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.practicum.explore.model.exception.*;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handlerEntityNumberFormatException(final NumberFormatException e) {
        String status = String.valueOf(HttpStatus.BAD_REQUEST);
        String reason = "Incorrectly made request.";
        String message = e.getMessage();
        LocalDateTime time = LocalDateTime.now();
        return new ApiError(message, reason, status, time);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiError handlerEntitySQL(final DataIntegrityViolationException e) {
        String status = String.valueOf(HttpStatus.CONFLICT);
        String reason = "Integrity constraint has been violated.";
        String message = e.getMessage();
        LocalDateTime time = LocalDateTime.now();
        return new ApiError(message, reason, status, time);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiError handlerEntityCustomConflictException(final CustomConflictException e) {
        String status = String.valueOf(HttpStatus.CONFLICT);
        String reason = "CustomConflictException.";
        String message = e.getMessage();
        LocalDateTime time = LocalDateTime.now();
        return new ApiError(message, reason, status, time);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handlerEntityCustomNotFoundException(final CustomNotFoundException e) {
        String status = String.valueOf(HttpStatus.NOT_FOUND);
        String reason = "The required object was not found.";
        String message = e.getMessage();
        LocalDateTime time = LocalDateTime.now();
        return new ApiError(message, reason, status, time);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handlerEntityMethodArgumentNotValidException(final MethodArgumentNotValidException e) {
        String status = String.valueOf(HttpStatus.BAD_REQUEST);
        String reason = "Incorrectly made request.";
        String message = e.getMessage();
        LocalDateTime time = LocalDateTime.now();
        return new ApiError(message, reason, status, time);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handlerEntityCustomValidException(final CustomValidException e) {
        String status = String.valueOf(HttpStatus.BAD_REQUEST);
        String reason = "Incorrectly made request.";
        String message = e.getMessage();
        LocalDateTime time = LocalDateTime.now();
        return new ApiError(message, reason, status, time);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiError handlerEntityEventStateException(final CustomForbiddenException e) {
        String status = String.valueOf(HttpStatus.CONFLICT);
        String reason = "For the requested operation the conditions are not met.";
        String message = e.getMessage();
        LocalDateTime time = LocalDateTime.now();
        return new ApiError(message, reason, status, time);
    }

}

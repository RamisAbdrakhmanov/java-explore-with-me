package ru.practicum.explore.model.exception;

public class CustomValidException extends RuntimeException {
    public CustomValidException(String message) {
        super(message);
    }
}

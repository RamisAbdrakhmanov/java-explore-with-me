package ru.practicum.explore.exception;

public class CustomValidException extends RuntimeException {
    public CustomValidException(String message) {
        super(message);
    }
}

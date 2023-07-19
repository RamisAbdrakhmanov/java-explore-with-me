package ru.practicum.explore.model.exception;

public class CustomConflictException extends RuntimeException{
    public CustomConflictException(String message) {
        super(message);
    }
}

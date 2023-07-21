package ru.practicum.explore.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiError {

    private final String status;
    private final String reason;
    private final String message;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime timestamp;

    public ApiError(String message, String reason, String status, LocalDateTime timestamp) {
        this.status = status;
        this.message = message;
        this.reason = reason;
        this.timestamp = timestamp;
    }

}

package ru.otus.kholudeev.dto.response;

import java.time.OffsetDateTime;

public record ApiErrorResponse(
        Integer code,
        String message,
        String path,
        OffsetDateTime timestamp
) {
    public ApiErrorResponse(Integer code, String message, String path) {
        this(code, message, path, OffsetDateTime.now());
    }
}
package ru.otus.kholudeev.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.OffsetDateTime;

@Schema(description = "Данные об ошибке")
public record ApiErrorResponse(
        @Schema(description = "Код ошибки")
        Integer code,
        @Schema(description = "Описание ошибки")
        String message,
        @Schema(description = "Путь конечной точки")
        String path,
        @Schema(description = "Временная метка")
        OffsetDateTime timestamp
) {
    public ApiErrorResponse(Integer code, String message, String path) {
        this(code, message, path, OffsetDateTime.now());
    }
}

package ru.otus.kholudeev.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Объект с информацией об ошибке сущности")
public class ApiObjectErrorResponse {
    @Schema(description = "Код ошибки")
    private Integer code;
    @Schema(description = "Описание ошибки")
    private String description;
}

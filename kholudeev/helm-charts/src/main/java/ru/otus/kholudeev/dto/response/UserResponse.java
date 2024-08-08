package ru.otus.kholudeev.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Schema(description = "Параметры пользователя")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserResponse {
    @JsonInclude(Include.NON_NULL)
    @Schema(description = "Внутренний идентификатор пользователя", example = "123456789")
    private Long id;

    @Schema(description = "Логин пользователя", example = "Ivan32")
    private String login;

    @Schema(description = "Имя пользователя", example = "Иван Иванов")
    private String name;

    @Schema(description = "Дата создания", example = "2020-01-01 00:00:00.000")
    private LocalDateTime creationDate;

    @JsonInclude(Include.NON_NULL)
    @Schema(description = "Объект с информацией об ошибке создания пользователя", nullable = true)
    private ApiObjectErrorResponse error;
}

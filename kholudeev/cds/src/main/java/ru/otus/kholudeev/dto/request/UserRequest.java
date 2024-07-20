package ru.otus.kholudeev.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Параметры пользователя")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserRequest {

    @NotBlank
    @Schema(description = "Логин пользователя", example = "Ivan32")
    private String login;

    @NotBlank
    @Schema(description = "Имя пользователя", example = "Иван Иванов")
    private String name;

    @NotBlank
    @Schema(description = "Пароль пользователя", example = "querty123")
    private String password;
}

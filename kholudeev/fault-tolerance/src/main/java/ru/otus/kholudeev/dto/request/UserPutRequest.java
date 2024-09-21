package ru.otus.kholudeev.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Schema(description = "Параметры пользователя")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserPutRequest {

    @NotBlank
    @Schema(description = "Логин пользователя", example = "Ivan32")
    private String login;

    @NotBlank
    @Schema(description = "Имя пользователя", example = "Иван Иванов")
    private String name;

    @NotNull
    @Schema(description = "Возраст пользователя", example = "31")
    private Integer age;
}

package ru.otus.kholudeev.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Schema(description = "Параметры пользователей")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UsersResponse {
    @Schema(description = "Массив пользователей")
    private List<UserResponse> users;
}

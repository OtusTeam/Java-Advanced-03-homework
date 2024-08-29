package ru.otus.kholudeev.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
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
public class UsersRequest {
    @NotEmpty
    @Valid
    @Schema(description = "Массив пользователей")
    private List<UserRequest> users;
}

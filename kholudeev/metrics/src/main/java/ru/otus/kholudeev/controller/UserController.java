package ru.otus.kholudeev.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.otus.kholudeev.dto.request.UserPutRequest;
import ru.otus.kholudeev.dto.request.UserRequest;
import ru.otus.kholudeev.dto.request.UsersRequest;
import ru.otus.kholudeev.dto.response.ApiErrorResponse;
import ru.otus.kholudeev.dto.response.UserResponse;
import ru.otus.kholudeev.dto.response.UsersResponse;
import ru.otus.kholudeev.facade.UserFacade;

@RestController
@AllArgsConstructor
@Tag(name = "User", description = "Контроллер для работы с пользователями")
@RequestMapping("/authorization")
public class UserController {
    private final UserFacade facade;

    @Operation(summary = "Массовое создание пользователей")
    @ApiResponse(responseCode = "200", description = "Пользователи созданы")
    @ApiResponse(responseCode = "400", description = "Указаны некорректные параметры",
            content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
            content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    @PostMapping("/users")
    public UsersResponse createAll(@Valid @RequestBody UsersRequest request) {
        return facade.createAll(request);
    }

    @Operation(summary = "Cоздание пользователя")
    @ApiResponse(responseCode = "200", description = "Пользователь создан")
    @ApiResponse(responseCode = "400", description = "Указаны некорректные параметры",
            content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
            content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    @PostMapping("/user")
    public UserResponse create(@Valid @RequestBody UserRequest request) {
        return facade.create(request);
    }

    @Operation(summary = "Получение пользователя")
    @Parameter(name = "id", required = true, description = "Внутренний идентификатор пользователя", in = ParameterIn.PATH)
    @ApiResponse(responseCode = "200", description = "Пользователь получен")
    @ApiResponse(responseCode = "400", description = "Указаны некорректные параметры",
            content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
            content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    @GetMapping("/user/{id}")
    public UserResponse getById(@PathVariable @NotNull Long id) {
        return facade.getById(id);
    }

    @Operation(summary = "Изменение пользователя")
    @Parameter(name = "id", required = true, description = "Внутренний идентификатор пользователя", example = "123456789", in = ParameterIn.PATH)
    @ApiResponse(responseCode = "200", description = "Пользователь изменен")
    @ApiResponse(responseCode = "400", description = "Указаны некорректные параметры",
            content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
            content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    @PutMapping("/user/{id}")
    public UserResponse update(@PathVariable @NotNull Long id, @Valid @RequestBody UserPutRequest userPutRequest) {
        return facade.update(id, userPutRequest);
    }

    @Operation(summary = "Удаление пользователя")
    @ApiResponse(responseCode = "200", description = "Пользователь удален")
    @ApiResponse(responseCode = "400", description = "Указаны некорректные параметры",
            content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
            content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    @DeleteMapping("/user/{login}")
    public UserResponse delete(@PathVariable("login") @NotBlank String login) {
        return facade.delete(login);
    }
}

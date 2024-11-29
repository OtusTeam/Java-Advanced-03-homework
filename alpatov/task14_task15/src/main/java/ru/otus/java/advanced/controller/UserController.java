package ru.otus.java.advanced.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.java.advanced.dto.UserDto;
import ru.otus.java.advanced.entity.User;
import ru.otus.java.advanced.mapper.UserMapper;
import ru.otus.java.advanced.service.UserRegisterService;
import ru.otus.java.advanced.service.UserService;

import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserRegisterService userRegisterService;
    private final UserService userService;
    private final UserMapper userMapper;

    @Operation(summary = "Register user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success register",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Error",
                    content = @Content) })
    @PostMapping(value = "user/register", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> register(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userRegisterService.register(userDto));
    }


    @Operation(summary = "Add user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success register",
                    content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDto.class))
            }),
            @ApiResponse(responseCode = "400",
                    description = "Error dto",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Error",
                    content = @Content) })
    @PostMapping(value = "user/add", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> add(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userRegisterService.register(userDto));
    }

    @Operation(summary = "Edit user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success update",
                    content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDto.class)) }),
            @ApiResponse(responseCode = "400",
                    description = "Error dto",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Error",
                    content = @Content) })
    @PostMapping(value = "user/edit/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> edit(@RequestBody UserDto userDto, @PathVariable UUID id) {
        return ResponseEntity.ok(userMapper.toDto(userService.save(userMapper.toEntity(userDto))));
    }

    @GetMapping(value = "user/get/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDto.class)) }),
            @ApiResponse(responseCode = "400",
                    description = "Error",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Not found",
                    content = @Content) })
    public ResponseEntity<UserDto> get(@PathVariable UUID id) {
        Optional<User> optionalUser = userService.findById(id);
        return optionalUser
                .map(user -> ResponseEntity.ok(userMapper.toDto(user)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}

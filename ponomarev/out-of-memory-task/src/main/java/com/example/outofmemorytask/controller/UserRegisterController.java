package com.example.outofmemorytask.controller;

import com.example.outofmemorytask.model.UserEntity;
import com.example.outofmemorytask.model.UserDto;
import com.example.outofmemorytask.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.entity.ContentType;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author Anton Ponomarev on 13.05.2024
 * @project Java-Advanced-homework
 */
@RestController
@RequiredArgsConstructor
@Slf4j
public class UserRegisterController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @PostMapping(path= "/register", consumes = "application/json", produces = "application/json")
    @ResponseBody
    UserDto register(@RequestBody UserDto userDto) throws ExecutionException, InterruptedException {
        CompletableFuture<UserEntity> userEntity = userService.create(convertToEntity(userDto));
        log.info("Register success: " + userEntity);
        return convertToDto(userEntity.get());
    }

    private UserEntity convertToEntity(UserDto userDto) {
        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
        return userEntity;
    }
    private UserDto convertToDto(UserEntity post) {
        UserDto userDto = modelMapper.map(post, UserDto.class);
        return userDto;
    }
}

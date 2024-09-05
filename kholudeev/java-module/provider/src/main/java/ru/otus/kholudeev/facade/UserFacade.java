package ru.otus.kholudeev.facade;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.kholudeev.dao.exception.UserExistsException;
import ru.otus.kholudeev.dao.service.UserExtend;
import ru.otus.kholudeev.dao.service.UserRepoService;
import ru.otus.kholudeev.dto.request.UserRequest;
import ru.otus.kholudeev.dto.response.UserResponse;
import ru.otus.kholudeev.exception.ParamValue;
import ru.otus.kholudeev.mapper.UserExtendMapper;

import java.util.*;

import static java.lang.String.format;
import static ru.otus.kholudeev.constant.ApiErrorResponseCodeConstant.UNKNOWN_ERROR;
import static ru.otus.kholudeev.constant.ApiErrorResponseCodeConstant.USER_EXISTS;

@Service
@AllArgsConstructor
@Slf4j
public class UserFacade {
    private final UserRepoService userRepoService;
    private final UserExtendMapper userMapper;

    @SneakyThrows
    private UserResponse handleException(UserRequest userRequest, Exception e) {
        if (e instanceof UserExistsException) {
            return userMapper.toUserResponseWithError(userRequest,
                    USER_EXISTS.getCode(), format(USER_EXISTS.getDescription(), "login", userRequest.getLogin()));
        } else {
            return userMapper.toUserResponseWithError(userRequest, UNKNOWN_ERROR.getCode(), UNKNOWN_ERROR.getDescription());
        }
    }

    public UserResponse getById(Long id) {
        log.info("Поиск пользователя по id - {}", id);
        try {
            UserExtend user = (UserExtend) userRepoService.getById(id);
            log.info("Пользователь получен по id - {}", id);
            return userMapper.toUserExtendResponse(user);
        } catch (Exception e) {
            log.error("Ошибка получения пользователя по внутреннему идентификатору, id - {}", id, e);
            throw e;
        }
    }

    public UserResponse create(UserRequest userRequest) {
        log.info("Начало создания пользователя, логин - {}", userRequest.getLogin());
        UserResponse userResponse;
        try {

            if (userRepoService.findByLogin(userRequest.getLogin()).isPresent()) {
                throw new UserExistsException(format("Пользователь с логином %s уже создан", userRequest.getLogin()),
                        Map.of("userField", ParamValue.valueOf("login"),
                                "userValue", ParamValue.valueOf(userRequest.getLogin())));
            }

            UserExtend user = userMapper.toExtendUser(userRequest);
            userRepoService.save(user);

            userResponse = userMapper.toUserExtendResponse(user);
            log.info("Пользователь успешно создан, id - {}, login - {}", user.getId(), user.getLogin());
        } catch (Exception e) {
            log.error("Ошибка создания пользователя, логин - {}", userRequest.getLogin(), e);

            userResponse = handleException(userRequest, e);
        }

        return userResponse;
    }
}

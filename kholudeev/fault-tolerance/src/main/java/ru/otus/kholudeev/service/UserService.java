package ru.otus.kholudeev.service;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.kholudeev.dao.exception.UserExistsException;
import ru.otus.kholudeev.dao.model.User;
import ru.otus.kholudeev.dao.service.UserRepoService;
import ru.otus.kholudeev.dto.request.UserPutRequest;
import ru.otus.kholudeev.dto.request.UserRequest;
import ru.otus.kholudeev.dto.request.UsersRequest;
import ru.otus.kholudeev.dto.response.UserResponse;
import ru.otus.kholudeev.dto.response.UsersResponse;
import ru.otus.kholudeev.exception.ParamValue;
import ru.otus.kholudeev.mapper.UserMapper;

import java.util.*;

import static java.lang.String.format;
import static ru.otus.kholudeev.constant.ApiErrorResponseCodeConstant.UNKNOWN_ERROR;
import static ru.otus.kholudeev.constant.ApiErrorResponseCodeConstant.USER_EXISTS;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {
    private final UserRepoService userRepoService;
    private final UserMapper userMapper;

    public UsersResponse createAll(UsersRequest request) {
        try {
            List<UserRequest> userRequests = request.getUsers();
            log.info("Начало создания пользователей в количестве - {}", userRequests.size());

            validateDuplicate(userRequests);

            List<UserResponse> userResponses = new ArrayList<>();
            userRequests.forEach(userRequest ->
                    userResponses.add(create(userRequest)));

            return new UsersResponse(userResponses);
        } catch (Exception e) {
            log.error("Ошибка создания пользователей", e);
            throw e;
        }
    }

    public UserResponse getById(Long id) {
        log.info("Поиск пользователя по id - {}", id);
        try {
            User user = userRepoService.getById(id);
            log.info("Пользователь получен по id - {}", id);
            return userMapper.toUserResponse(user);
        } catch (Exception e) {
            log.error("Ошибка получения пользователя по внутреннему идентификатору, id - {}", id, e);
            throw e;
        }
    }

    @Transactional
    public UserResponse update(Long id, UserPutRequest userPutRequest) {
        log.info("Изменение пользователя, id - {}", id);
        try {
            User user = userRepoService.getById(id);
            user.setName(userPutRequest.getName());
            user.setLogin(userPutRequest.getLogin());
            user.setAge(userPutRequest.getAge());
            userRepoService.save(user);
            log.info("Пользователь изменен, id - {}", id);
            return userMapper.toUserResponse(user);
        } catch (Exception e) {
            log.error("Ошибка изменения пользователя по ид, id - {}", id, e);
            throw e;
        }
    }

    @Transactional
    public UserResponse delete(String login) {
        log.info("Удаление пользователя, login - {}", login);
        try {
            User user = userRepoService.getByLogin(login);
            userRepoService.deleteByLogin(login);
            log.info("Пользователь удален, login - {}", login);
            return userMapper.toUserResponse(user);
        } catch (Exception e) {
            log.error("Ошибка удаления пользователя по логину, login - {}", login, e);
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

            User user = userMapper.toUser(userRequest);
            userRepoService.save(user);
            userResponse = userMapper.toUserResponse(user);
            log.info("Пользователь успешно создан, id - {}, login - {}", user.getId(), user.getLogin());
        } catch (Exception e) {
            log.error("Ошибка создания пользователя, логин - {}", userRequest.getLogin(), e);

            userResponse = handleException(userRequest, e);
        }

        return userResponse;
    }

    @SneakyThrows
    private UserResponse handleException(UserRequest userRequest, Exception e) {
        if (e instanceof UserExistsException) {
            return userMapper.toUserResponseWithError(userRequest,
                    USER_EXISTS.getCode(), format(USER_EXISTS.getDescription(), "login", userRequest.getLogin()));
        } else {
            return userMapper.toUserResponseWithError(userRequest, UNKNOWN_ERROR.getCode(), UNKNOWN_ERROR.getDescription());
        }
    }

    private void validateDuplicate(List<UserRequest> userRequests) {
        Set<String> externalIds = new HashSet<>();
        Set<String> codes = new HashSet<>();

        for (UserRequest userRequest : userRequests) {
            if (!externalIds.add(userRequest.getLogin()) ||
                    !codes.add(userRequest.getLogin())) {
                throw new IllegalArgumentException("В параметрах запроса есть записи с дубликатами логина");
            }
        }
    }
}

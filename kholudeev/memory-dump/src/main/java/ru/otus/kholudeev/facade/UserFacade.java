package ru.otus.kholudeev.facade;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.kholudeev.dao.model.User;
import ru.otus.kholudeev.dao.service.UserRepoService;
import ru.otus.kholudeev.dto.request.UserPutRequest;
import ru.otus.kholudeev.dto.request.UserRequest;
import ru.otus.kholudeev.dto.request.UsersRequest;
import ru.otus.kholudeev.dto.response.UserResponse;
import ru.otus.kholudeev.dto.response.UsersResponse;
import ru.otus.kholudeev.exception.UserExistsException;
import ru.otus.kholudeev.mapper.UserMapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.lang.String.format;
import static ru.otus.kholudeev.constant.ApiErrorResponseCodeConstant.UNKNOWN_ERROR;
import static ru.otus.kholudeev.constant.ApiErrorResponseCodeConstant.USER_EXISTS;

@Service
@AllArgsConstructor
@Slf4j
public class UserFacade {
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

    @SneakyThrows
    private UserResponse handleException(UserRequest userRequest, Exception e) {
        if (e instanceof UserExistsException) {
            return userMapper.toUserResponseWithError(userRequest,
                    USER_EXISTS.getCode(), USER_EXISTS.getDescription());
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

    public UserResponse getById(Long id) {
        try {
            log.info("Поиск пользователя по id - {}", id);
            User user = userRepoService.getById(id);
            return userMapper.toUserResponse(user);
        } catch (Exception e) {
            log.error("Ошибка получения пользователя по внутреннему идентификатору, id - {}", id, e);
            throw e;
        }
    }

    @Transactional
    public UserResponse update(Long id, UserPutRequest userPutRequest) {
        log.info("Изменение пользователя, id - {}", id);

        User user = userRepoService.getById(id);
        user.setName(userPutRequest.getName());
        user.setLogin(userPutRequest.getLogin());
        userRepoService.save(user);

        return userMapper.toUserResponse(user);
    }

    @Transactional
    public void delete(String login) {
        log.info("Удаление пользователя, login - {}", login);

        userRepoService.deleteByLogin(login);
    }

    public UserResponse create(UserRequest userRequest) {
        log.info("Начало создания пользователя, логин - {}", userRequest.getLogin());
        UserResponse userResponse;
        try {

            if (userRepoService.findByLogin(userRequest.getLogin()).isPresent()) {
                throw new UserExistsException(format("Пользователь с логином %s уже создан", userRequest.getLogin()));
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
}

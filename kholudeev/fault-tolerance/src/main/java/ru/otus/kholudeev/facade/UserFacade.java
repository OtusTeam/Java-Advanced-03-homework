package ru.otus.kholudeev.facade;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.kholudeev.dto.request.UserPutRequest;
import ru.otus.kholudeev.dto.request.UserRequest;
import ru.otus.kholudeev.dto.request.UsersRequest;
import ru.otus.kholudeev.dto.response.UserResponse;
import ru.otus.kholudeev.dto.response.UsersResponse;
import ru.otus.kholudeev.service.UserService;

@Service
@AllArgsConstructor
@Slf4j
public class UserFacade {
    private final UserService userService;

    @RateLimiter(name = "rpsRateLimiter")
    public UserResponse getByIdWithRps(Long id) {
        return getById(id);
    }

    @CircuitBreaker(name = "test")
    public UserResponse getByIdWithCircuitBreaker(Long id) {
        return getById(id);
    }

    public UsersResponse createAll(UsersRequest request) {
        return userService.createAll(request);
    }

    public UserResponse getById(Long id) {
        return userService.getById(id);
    }

    public UserResponse update(Long id, UserPutRequest userPutRequest) {
        return userService.update(id, userPutRequest);
    }

    public UserResponse delete(String login) {
        return userService.delete(login);
    }

    public UserResponse create(UserRequest userRequest) {
        return userService.create(userRequest);
    }
}

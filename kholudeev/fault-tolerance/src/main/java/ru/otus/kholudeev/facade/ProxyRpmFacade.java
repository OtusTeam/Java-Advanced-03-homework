package ru.otus.kholudeev.facade;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.kholudeev.dto.response.UserResponse;

@Service
@AllArgsConstructor
public class ProxyRpmFacade {
    private final UserFacade userFacade;

    @RateLimiter(name = "rpmRateLimiter")
    public UserResponse getByIdWithRpm(Long id) {
        return userFacade.getByIdWithRps(id);
    }
}

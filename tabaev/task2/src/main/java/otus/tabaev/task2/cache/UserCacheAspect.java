package otus.tabaev.task2.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import otus.tabaev.task2.controller.dto.CreateUserRequest;

import java.util.concurrent.TimeUnit;

@Aspect
@Component
public class UserCacheAspect {


    private Cache<String, UserDetails> userCache = Caffeine
            .newBuilder()
            .expireAfterWrite(30, TimeUnit.MINUTES)
            .maximumSize(1000)
            .build();

    @Before("execution(* otus.tabaev.task2.service.MyUserService.createUser(..)) && args(request)")
    public void beforeCreateUser(CreateUserRequest request) {
        if (userCache.getIfPresent(request.getLogin()) != null) {
            throw new IllegalArgumentException("User with username: " + request.getLogin() + " already exists.");
        }
    }

    @AfterReturning(value = "execution(* otus.tabaev.task2.service.MyUserService.createUser(..))", returning = "userDetails")
    public void AfterCreateUser(UserDetails userDetails) {
        userCache.put(userDetails.getUsername(), userDetails);
    }

}

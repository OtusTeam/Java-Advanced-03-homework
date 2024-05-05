package otus.tabaev.task2.cache;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import otus.tabaev.task2.controller.dto.CreateUserRequest;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Aspect
@Component
public class UserCacheAspect {

    private Map<String, UserDetails> userCache = new ConcurrentHashMap<>();

    @Before("execution(* otus.tabaev.task2.service.MyUserService.createUser(..)) && args(request)")
    public void beforeCreateUser(CreateUserRequest request) {
        if (userCache.containsKey(request.getLogin())) {
            throw new IllegalArgumentException("User with username: " + request.getLogin() + " already exists.");
        }
    }

    @AfterReturning(value = "execution(* otus.tabaev.task2.service.MyUserService.createUser(..))", returning = "userDetails")
    public void AfterCreateUser(UserDetails userDetails){
        userCache.put(userDetails.getUsername(), userDetails);
    }

}
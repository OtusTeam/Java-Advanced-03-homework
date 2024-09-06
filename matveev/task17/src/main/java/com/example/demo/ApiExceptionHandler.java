package com.example.demo;

import io.github.resilience4j.bulkhead.BulkheadFullException;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.concurrent.TimeoutException;

@ControllerAdvice
@Slf4j
public class ApiExceptionHandler {

    @ExceptionHandler({RequestNotPermitted.class})
    @ResponseStatus(HttpStatus.TOO_MANY_REQUESTS)
    public void handleRequestNotPermitted() { log.warn("Too Many Requests"); }
    @ExceptionHandler({CallNotPermittedException.class})
    @ResponseStatus (HttpStatus.SERVICE_UNAVAILABLE)
    public void handleCallNotPermittedException() { log.warn("Service Unavailable"); }
    @ExceptionHandler({IllegalStateException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void handleIllegalStateException() { log.warn("Internal Server Error"); }
}

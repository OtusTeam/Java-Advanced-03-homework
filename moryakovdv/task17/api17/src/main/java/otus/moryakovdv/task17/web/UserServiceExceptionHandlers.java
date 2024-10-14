package otus.moryakovdv.task17.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice(assignableTypes = {UserController.class})
public class UserServiceExceptionHandlers {
	
	@ExceptionHandler(RequestNotPermitted.class)
	public ResponseEntity<String> requestNotPermittedExceptionHandler(HttpServletRequest request,
			RequestNotPermitted e) {
		log.warn("Too many requests triggered");
		return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body("Too many requests");
	}

	@ExceptionHandler(CallNotPermittedException.class)
	public ResponseEntity<String> callNotPermittedExceptionHandler(HttpServletRequest request,
			CallNotPermittedException e) {
		log.warn("Call not permitted by Circuit breaker");
		return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("Circuit breaker CLOSED");
	}

}

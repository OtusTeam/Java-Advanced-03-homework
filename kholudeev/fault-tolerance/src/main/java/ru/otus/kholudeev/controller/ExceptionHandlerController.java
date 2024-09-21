package ru.otus.kholudeev.controller;

import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.otus.kholudeev.dao.exception.*;
import ru.otus.kholudeev.dto.response.ApiErrorResponse;
import ru.otus.kholudeev.dto.response.BaseArrayResponse;
import ru.otus.kholudeev.exception.*;

import static java.lang.String.format;
import static ru.otus.kholudeev.constant.ApiErrorResponseCodeConstant.*;

@RestControllerAdvice
@Slf4j
public class ExceptionHandlerController {
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiErrorResponse> parseRequestExceptionHandler(HttpServletRequest request, HttpMessageNotReadableException e) {
        log.error("Ошибка парсинга тела запроса", e);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiErrorResponse(HTTP_MESSAGE_NOT_READABLE.getCode(),
                        HTTP_MESSAGE_NOT_READABLE.getDescription(),
                        request.getRequestURI()));
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ApiErrorResponse> requestParamNotFoundExceptionHandler(HttpServletRequest request, MissingServletRequestParameterException e) {
        log.error("Обязательный параметр запроса не задан. Имя: {}. Тип: {}", e.getParameterName(), e.getParameterType(), e);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiErrorResponse(MISSING_SERVLET_REQUEST_PARAMETER.getCode(),
                        MISSING_SERVLET_REQUEST_PARAMETER.getDescription(),
                        request.getRequestURI()));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiErrorResponse> constraintViolationExceptionHandler(HttpServletRequest request, ConstraintViolationException e) {
        log.error("Ошибка парсинга тела запроса", e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiErrorResponse(HTTP_MESSAGE_NOT_READABLE.getCode(),
                        HTTP_MESSAGE_NOT_READABLE.getDescription(),
                        request.getRequestURI()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> requestParamNotFoundExceptionHandler(HttpServletRequest request, MethodArgumentNotValidException e) {
        log.error("Параметр в теле запроса имеет неверный формат. Имя: {}", e.getParameter(), e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiErrorResponse(METHOD_ARGUMENT_NOT_VALID.getCode(),
                        METHOD_ARGUMENT_NOT_VALID.getDescription(),
                        request.getRequestURI()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> exceptionHandler(HttpServletRequest request, Exception e) {
        log.error("Ошибка в работе сервиса", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiErrorResponse(UNKNOWN_ERROR.getCode(),
                        UNKNOWN_ERROR.getDescription(),
                        request.getRequestURI()));
    }

    @ExceptionHandler(NoArrayObjectsProcessedException.class)
    public ResponseEntity<BaseArrayResponse<?, ?>> noArrayObjectsProcessedExceptionHandler(NoArrayObjectsProcessedException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(e.getBaseArrayResponse());
    }

    @ExceptionHandler(PartialArrayObjectsProcessedException.class)
    public ResponseEntity<BaseArrayResponse<?, ?>> partialArrayObjectsProcessedExceptionHandler(PartialArrayObjectsProcessedException e) {
        return ResponseEntity.status(HttpStatus.MULTI_STATUS)
                .body(e.getBaseArrayResponse());
    }

    @ExceptionHandler(UserExistsException.class)
    public ResponseEntity<ApiErrorResponse> userExistsExceptionHandler(HttpServletRequest request, UserExistsException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiErrorResponse(USER_EXISTS.getCode(),
                        format(USER_EXISTS.getDescription(), e.getParams().get("userField").value(), e.getParams().get("userValue").value()),
                        request.getRequestURI()));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> userNotFoundExceptionExceptionHandler(HttpServletRequest request, UserNotFoundException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiErrorResponse(USER_NOT_FOUND.getCode(),
                        format(USER_NOT_FOUND.getDescription(), e.getParams().get("userField").value(), e.getParams().get("userValue").value()),
                        request.getRequestURI()));
    }

    @ExceptionHandler(RequestNotPermitted.class)
    public ResponseEntity<ApiErrorResponse> requestNotPermittedExceptionHandler(HttpServletRequest request, RequestNotPermitted e) {
        log.warn("Too many requests");
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                .body(new ApiErrorResponse(-1,
                        format("Too many requests %s", e.getMessage()),
                        request.getRequestURI()));
    }

    @ExceptionHandler(CallNotPermittedException.class)
    public ResponseEntity<ApiErrorResponse> callNotPermittedExceptionHandler(HttpServletRequest request, CallNotPermittedException e) {
        log.warn("Call not permitted");
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(new ApiErrorResponse(-1,
                        format("Call not permited %s", e.getMessage()),
                        request.getRequestURI()));
    }
}

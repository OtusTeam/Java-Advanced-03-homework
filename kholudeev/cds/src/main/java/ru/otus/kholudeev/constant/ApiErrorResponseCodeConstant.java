package ru.otus.kholudeev.constant;

import lombok.Getter;

@Getter
public enum ApiErrorResponseCodeConstant {
    UNKNOWN_ERROR(-1, "Ошибка в работе сервиса"),
    HTTP_MESSAGE_NOT_READABLE(-2, "Ошибка парсинга тела запроса"),
    MISSING_SERVLET_REQUEST_PARAMETER(-3, "Обязательный параметр запроса не задан"),
    METHOD_ARGUMENT_NOT_VALID(-4, "Параметр в теле запроса имеет неверный формат"),
    USER_EXISTS(1101, "Пользователь с такими параметрами уже существует"),
    USER_NOT_FOUND(1100, "Не найден пользователь");

    private final Integer code;
    private final String description;

    ApiErrorResponseCodeConstant(Integer code, String description) {
        this.code = code;
        this.description = description;
    }
}

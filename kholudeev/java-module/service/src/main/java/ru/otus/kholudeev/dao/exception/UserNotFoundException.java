package ru.otus.kholudeev.dao.exception;

import ru.otus.kholudeev.exception.ParamValue;

import java.util.Map;

public class UserNotFoundException extends DaoException {
    public UserNotFoundException(String message, Map<String, ParamValue> params ) {
        super(message, params);
    }
}

package ru.otus.kholudeev.dao.exception;

import ru.otus.kholudeev.exception.ApplicationException;
import ru.otus.kholudeev.exception.ParamValue;

import java.util.Map;

public class UserExistsException extends ApplicationException {
    public UserExistsException(String message, Map<String, ParamValue> params ) {
        super(message, params);
    }
}

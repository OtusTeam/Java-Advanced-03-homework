package ru.otus.kholudeev.dao.exception;

import ru.otus.kholudeev.exception.ParamValue;
import ru.otus.kholudeev.exception.ParameterizedException;

import java.util.Map;

public class DaoException extends ParameterizedException {

    public DaoException(String message, Map<String, ParamValue> params) {
        super(message, params);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoException() {
    }
}

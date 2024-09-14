package ru.otus.kholudeev.dao.exception;

import ru.otus.kholudeev.exception.ParamValue;
import ru.otus.kholudeev.exception.ParameterizedException;

import java.util.Map;

public class DaoException extends ParameterizedException {
    public DaoException(String message, Throwable cause, Map<String, ParamValue> params) {
        super(message, cause, params);
    }
    public DaoException(Throwable cause, Map<String, ParamValue> params) {
        super(cause, params);
    }

    public DaoException(String message, Map<String, ParamValue> params) {
        super(message, params);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoException(Throwable cause) {
        super(cause);
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException(Map<String, ParamValue> params) {
        super(params);
    }

    public DaoException() {
    }
}

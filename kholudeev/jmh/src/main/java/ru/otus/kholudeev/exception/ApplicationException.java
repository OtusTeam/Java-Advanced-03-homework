package ru.otus.kholudeev.exception;

import java.util.Map;

public class ApplicationException extends ParameterizedException {
    public ApplicationException(String message, Throwable cause, Map<String, ParamValue> params) {
        super(message, cause, params);
    }

    public ApplicationException(Throwable cause, Map<String, ParamValue> params) {
        super(cause, params);
    }

    public ApplicationException(String message, Map<String, ParamValue> params) {
        super(message, params);
    }

    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApplicationException(Throwable cause) {
        super(cause);
    }

    public ApplicationException(String message) {
        super(message);
    }

    public ApplicationException(Map<String, ParamValue> params) {
        super(params);
    }

    public ApplicationException() {
    }
}


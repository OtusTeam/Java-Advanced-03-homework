package ru.otus.kholudeev.exception;

import lombok.Getter;

import java.util.Map;

import static java.util.Collections.unmodifiableMap;

@Getter
public class ParameterizedException extends RuntimeException {
    private final transient Map<String, ParamValue> params;

    public ParameterizedException(String message, Throwable cause, Map<String, ParamValue> params) {
        super(message);
        super.initCause(cause);

        this.params = params != null ? unmodifiableMap(params) : null;
    }

    public ParameterizedException(Throwable cause, Map<String, ParamValue> params) {
        this(null, cause, params);
    }

    public ParameterizedException(String message, Map<String, ParamValue> params) {
        this(message, null, params);
    }

    public ParameterizedException(String message, Throwable cause) {
        this(message, cause, null);
    }

    public ParameterizedException(Throwable cause) {
        this(null, cause, null);
    }

    public ParameterizedException(String message) {
        this(message, null, null);
    }

    public ParameterizedException(Map<String, ParamValue> params) {
        this(null, null, params);
    }

    public ParameterizedException() {
        params = null;
    }
}

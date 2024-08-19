package ru.otus.kholudeev.exception;

public record ParamValue(Object value, String message) {
    public static ParamValue valueOf(Object value) {
        return new ParamValue(value, null);
    }

    public static ParamValue valueOf(Object value, String message) {
        return new ParamValue(value, message);
    }
}

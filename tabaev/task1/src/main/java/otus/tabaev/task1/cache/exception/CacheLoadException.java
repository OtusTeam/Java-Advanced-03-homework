package otus.tabaev.task1.cache.exception;

public class CacheLoadException extends Exception {

    public CacheLoadException(String message) {
        super(message);
    }

    public CacheLoadException(String message, Throwable cause) {
        super(message, cause);
    }
}

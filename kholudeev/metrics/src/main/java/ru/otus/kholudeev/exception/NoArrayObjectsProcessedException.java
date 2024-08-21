package ru.otus.kholudeev.exception;

import lombok.Getter;
import ru.otus.kholudeev.dto.response.BaseArrayResponse;

@Getter
public class NoArrayObjectsProcessedException extends RuntimeException {
    private final transient BaseArrayResponse<?, ?> baseArrayResponse;

    public NoArrayObjectsProcessedException(BaseArrayResponse<?, ?> baseArrayResponse) {
        this.baseArrayResponse = baseArrayResponse;
    }
}

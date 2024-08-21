package ru.otus.kholudeev.exception;

import lombok.Getter;
import ru.otus.kholudeev.dto.response.BaseArrayResponse;


@Getter
public class PartialArrayObjectsProcessedException extends RuntimeException {
    private final transient BaseArrayResponse<?, ?> baseArrayResponse;

    public PartialArrayObjectsProcessedException(BaseArrayResponse<?, ?> baseArrayResponse) {
        this.baseArrayResponse = baseArrayResponse;
    }
}

package ru.otus.kholudeev.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.kholudeev.dto.response.BaseArrayResponse;
import ru.otus.kholudeev.exception.NoArrayObjectsProcessedException;
import ru.otus.kholudeev.exception.PartialArrayObjectsProcessedException;

@Service
@AllArgsConstructor
public class ArrayResponseValidator {
    public void validate(BaseArrayResponse<?, ?> baseArrayResponse) {
        if (baseArrayResponse.getErrors().isEmpty() && baseArrayResponse.getEntities().isEmpty()) {
            throw new IllegalArgumentException("Переданное значение не содержит ни одного объекта");
        }
        if (baseArrayResponse.getErrors().isEmpty()) {
            return;
        }
        if (baseArrayResponse.getEntities().isEmpty()) {
            throw new NoArrayObjectsProcessedException(baseArrayResponse);
        } else {
            throw new PartialArrayObjectsProcessedException(baseArrayResponse);
        }
    }
}

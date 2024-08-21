package ru.otus.kholudeev.aspect;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import ru.otus.kholudeev.dto.response.BaseArrayResponse;
import ru.otus.kholudeev.service.ArrayResponseValidator;

@Aspect
@RequiredArgsConstructor
@Component
public class ArrayResponseValidatorAspect {
    private final ArrayResponseValidator arrayProcessingValidator;

    @AfterReturning(pointcut = "@annotation(ru.otus.kholudeev.aspect.annotation.ValidateArrayResponse)", returning = "response")
    public Object validateResponse(BaseArrayResponse<?, ?> response) {
        if (response == null) {
            throw new IllegalArgumentException("Ожидаемый экземпляр baseArrayResponse равен null");
        } else {
            arrayProcessingValidator.validate(response);
        }
        return response;
    }
}


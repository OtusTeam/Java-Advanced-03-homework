package ru.otus.kholudeev.aspect.annotation;

import ru.otus.kholudeev.dto.response.BaseArrayResponse;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация для валидации респонса с целью определения кода состояния ответа HTTP
 * Применяется к методу, который возвращает класс, наследуемый от {@link BaseArrayResponse}
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidateArrayResponse {
}


package ru.otus.kholudeev.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class BaseArrayResponse<O, E> {
    private List<O> entities;

    @Schema(description = "Массив объектов с ошибками")
    private List<E> errors;
}
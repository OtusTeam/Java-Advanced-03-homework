package ru.otus.kholudeev.dto.response;

import lombok.Data;

@Data
public class ApiObjectErrorResponse {
    private Integer code;
    private String description;
}

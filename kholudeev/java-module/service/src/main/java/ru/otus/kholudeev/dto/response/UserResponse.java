package ru.otus.kholudeev.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserResponse {
    @JsonInclude(Include.NON_NULL)
    private Long id;

    private String login;

    private String name;

    private LocalDateTime creationDate;

    @JsonInclude(Include.NON_NULL)
    private ApiObjectErrorResponse error;
}
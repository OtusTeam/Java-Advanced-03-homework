package otus.tabaev.task11.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ErrorResponse {

    @JsonProperty("error_description")
    private String errorDescription;

    @JsonProperty("success")
    private boolean success;
}

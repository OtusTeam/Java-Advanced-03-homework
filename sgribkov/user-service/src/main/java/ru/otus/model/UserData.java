package ru.otus.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@AllArgsConstructor
@Schema
public class UserData {
    @Schema(description = "User id for monitoring")
    private String id;
    @Schema(description = "User login")
    private String login;
    @Schema(description = "User last monitoring timestamp")
    private Instant lastMonitoring;
}

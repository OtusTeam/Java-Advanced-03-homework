package ru.otus.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@AllArgsConstructor
public class UserData {
    private String id;
    private String login;
    private String lastMonitoring;
}

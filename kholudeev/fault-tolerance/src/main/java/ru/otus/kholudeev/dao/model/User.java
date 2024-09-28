package ru.otus.kholudeev.dao.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

/**
 * Сущность пользователь
 */
@Entity
@Table(name = "USER")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    /**
     * Идентификатор сущности
     */
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_GENERATOR")
    @SequenceGenerator(name = "USER_GENERATOR", sequenceName = "USER_SEQ", allocationSize = 1)
    private Long id;
    /**
     * Логин пользователя
     */
    @Column(name = "LOGIN")
    private String login;
    /**
     * Имя пользователя
     */
    @Column(name = "NAME")
    private String name;
    /**
     * Дата начала действия
     */
    @Column(name = "PASSWORD")
    private String password;
    /**
     * Возраст
     */
    @Column(name = "AGE")
    private Integer age;
    /**
     * Дата создания
     */
    @CreationTimestamp
    @Column(name = "CREATION_DATE")
    private LocalDateTime creationDate;
}

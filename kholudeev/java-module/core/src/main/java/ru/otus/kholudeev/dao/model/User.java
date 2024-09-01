package ru.otus.kholudeev.dao.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

/**
 * Сущность пользователь
 */
@MappedSuperclass
@Table(name = "USER")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class User {
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
     * Дата создания
     */
    @CreationTimestamp
    @Column(name = "CREATION_DATE")
    private LocalDateTime creationDate;

}

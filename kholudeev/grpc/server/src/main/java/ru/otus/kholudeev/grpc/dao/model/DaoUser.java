package ru.otus.kholudeev.grpc.dao.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

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
public class DaoUser {
    @ManyToMany
    @JoinTable(
            name = "USER_PRODUCT",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "PRODUCT_ID"))
    Set<DaoProduct> products;
    /**
     * Идентификатор сущности
     */
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_GENERATOR")
    @SequenceGenerator(name = "USER_GENERATOR", sequenceName = "USER_SEQ", allocationSize = 1)
    private Long id;
    /**
     * E-mail пользователя
     */
    @Column(name = "EMAIL")
    private String email;
    /**
     * Имя пользователя
     */
    @Column(name = "NAME")
    private String name;
    /**
     * Дата создания
     */
    @CreationTimestamp
    @Column(name = "CREATION_DATE")
    private LocalDateTime creationDate;
}

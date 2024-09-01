package ru.otus.kholudeev.grpc.dao.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

/**
 * Сущность продукт
 */
@Entity
@Table(name = "PRODUCT")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DaoProduct {
    @ManyToMany(mappedBy = "products")
    Set<DaoUser> users;
    /**
     * Идентификатор сущности
     */
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUCT_GENERATOR")
    @SequenceGenerator(name = "PRODUCT_GENERATOR", sequenceName = "PRODUCT_SEQ", allocationSize = 1)
    private Long id;
    /**
     * Наименование продукта
     */
    @Column(name = "NAME")
    private String name;
}

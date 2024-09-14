package ru.otus.model;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "card_data")
public class CardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "card")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @Nonnull
    private UserEntity user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "card")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Nonnull
    private List<CardItemEntity> cardItems;

    public CardEntity(UserEntity user) {
        this.user = user;
        this.cardItems = new ArrayList<CardItemEntity>();
    }
}

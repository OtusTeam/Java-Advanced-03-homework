package ru.otus.model;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Data
@NoArgsConstructor
@Table(name = "card_item_data")
public class CardItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JoinColumn(name = "card_id", nullable = false)
    @Nonnull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private CardEntity card;

    @JoinColumn(name = "product_id", nullable = false)
    @Nonnull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private ProductEntity product;

    public CardItemEntity(CardEntity card, ProductEntity product) {
        this.card = card;
        this.product = product;
    }
}

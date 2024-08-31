package ru.otus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.model.CardItemEntity;

@Repository
public interface CardItemRepository extends JpaRepository<CardItemEntity, Long> {
}

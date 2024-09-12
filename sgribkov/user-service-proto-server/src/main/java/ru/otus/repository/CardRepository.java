package ru.otus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.model.CardEntity;

@Repository
public interface CardRepository extends JpaRepository<CardEntity, Long> {
}

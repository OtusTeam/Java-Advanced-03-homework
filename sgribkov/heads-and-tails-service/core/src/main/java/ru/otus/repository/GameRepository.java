package ru.otus.repository;

import ru.otus.model.Game;

import java.util.List;

public interface GameRepository {
    void save(Game game);
    List<Game> getAll();
}

package ru.otus.repository;

import ru.otus.model.Game;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class ImMemoryGameRepository implements GameRepository {
    private final Map<String, Game> history = new ConcurrentHashMap<>();

    @Override
    public void save(Game game) {
        String gameId = UUID.randomUUID().toString();
        history.put(gameId, game);
    }

    @Override
    public List<Game> getAll() {
        return history.values().stream().toList();
    }
}

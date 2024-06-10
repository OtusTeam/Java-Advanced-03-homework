package ru.otus.game;

import ru.otus.model.Game;

import java.util.List;

public interface GameService {
    String play(int prediction);
    List<String> getStat();
}

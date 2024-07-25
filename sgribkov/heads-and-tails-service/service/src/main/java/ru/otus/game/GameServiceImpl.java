package ru.otus.game;

import lombok.NoArgsConstructor;
import ru.otus.model.Game;
import ru.otus.repository.*;
import ru.otus.factgen.*;

import java.util.List;

@NoArgsConstructor
public class GameServiceImpl implements GameService {
    private final GameRepository repository = new ImMemoryGameRepository();
    private final FactGenerator<Integer> factGen = new FactGeneratorInt(0, 1);

    @Override
    public String play(int prediction) {
        Game game = new Game(prediction);
        var fact = factGen.get();
        game.setFact(fact);
        repository.save(game);
        return game.toString();
    }

    @Override
    public List<String> getStat() {
        return repository.getAll()
                .stream()
                .map(Game::toString)
                .toList();
    }
}

package ru.otus.app;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.otus.game.GameService;
import ru.otus.game.GameServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/")
@AllArgsConstructor
public class GameController {
    private final GameService gameService = new GameServiceImpl();

    @PostMapping("/play")
    public ResponseEntity<String> play(Model model,
                                       @RequestBody PredictionDto prediction) {
        String gameResult = gameService.play(prediction.getPrediction());
        return ResponseEntity.ok(gameResult);
    }

    @GetMapping("/stat")
    public ResponseEntity<List<String>> getStat() {
        List<String> gamesStat = gameService.getStat();
        return ResponseEntity.ok(gamesStat);
    }
}

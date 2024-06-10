package ru.otus.factgen;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.concurrent.ThreadLocalRandom;

@Setter
@Getter
public class FactGeneratorInt implements FactGenerator<Integer> {
    private final Integer min;
    private final Integer max;

    public FactGeneratorInt(Integer min, Integer max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public Integer get() {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}

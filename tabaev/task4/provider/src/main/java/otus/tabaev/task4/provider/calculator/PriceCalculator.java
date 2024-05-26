package otus.tabaev.task4.provider.calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PriceCalculator {

    private static PriceCalculator instance;

    private PriceCalculator() {}

    public static synchronized PriceCalculator getInstance() {
        if (instance == null) {
            instance = new PriceCalculator();
        }
        return instance;
    }

    public BigDecimal generateRandomBigDecimalFromRange(BigDecimal min, BigDecimal max) {
        BigDecimal randomBigDecimal = min.add(BigDecimal.valueOf(Math.random()).multiply(max.subtract(min)));
        return randomBigDecimal.setScale(2, RoundingMode.HALF_UP);
    }
}

package otus.tabaev.task4.provider;

import otus.tabaev.task4.provider.calculator.PriceCalculator;

import java.math.BigDecimal;
public class GenerateQuoteProvider implements QuoteProvider {

    private static GenerateQuoteProvider instance;

    private GenerateQuoteProvider() {
    }

    public static synchronized GenerateQuoteProvider getInstance() {
        if (instance == null) {
            instance = new GenerateQuoteProvider();
        }
        return instance;
    }


    @Override
    public BigDecimal getCurrentPriceByTicketName(String ticketName) {
        PriceCalculator priceCalculator = PriceCalculator.getInstance();

        switch (ticketName) {
            case "SBER":
                return priceCalculator.generateRandomBigDecimalFromRange(BigDecimal.valueOf(139.00), BigDecimal.valueOf(325.00));
            case "GAZP":
                return priceCalculator.generateRandomBigDecimalFromRange(BigDecimal.valueOf(132.00), BigDecimal.valueOf(135.00));
            case "LKOH":
                return priceCalculator.generateRandomBigDecimalFromRange(BigDecimal.valueOf(7500), BigDecimal.valueOf(7800));
            case "RUAL":
                return priceCalculator.generateRandomBigDecimalFromRange(BigDecimal.valueOf(43.00), BigDecimal.valueOf(46.50));
            default:
                return priceCalculator.generateRandomBigDecimalFromRange(BigDecimal.valueOf(0), BigDecimal.valueOf(100000));
        }
    }

}

package otus.tabaev.task4.provider;

import java.math.BigDecimal;

public interface QuoteProvider {

    BigDecimal getCurrentPriceByTicketName(String ticketName);
}


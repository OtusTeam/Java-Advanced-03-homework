package otus.tabaev.task4.api;

import otus.tabaev.task4.provider.GenerateQuoteProvider;
import otus.tabaev.task4.provider.QuoteProvider;
import otus.tabaev.task4.service.QuoteService;
import otus.tabaev.task4.service.dto.QuoteCreateRequest;
import otus.tabaev.task4.service.dto.QuoteInfoResponse;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class Monitor {

    private static Monitor instance;

    private final QuoteService quoteService;

    private final QuoteProvider quoteProvider;

    private Monitor(QuoteService quoteService, QuoteProvider quoteProvider) {
        this.quoteProvider = quoteProvider;
        this.quoteService = quoteService;
    }

    public static synchronized Monitor getInstance() {
        if (instance == null) {
            instance = new Monitor(QuoteService.getInstance(), GenerateQuoteProvider.getInstance());
        }
        return instance;
    }


    public void printQuotesInfo(String ticketName) {

        List<QuoteInfoResponse> quotes = quoteService.getLastNQuotesByTicketName(ticketName, 5);

        System.out.println("---------------------------------------------------------------------");

        System.out.println("Ticket Name: " + ticketName);
        quotes.forEach(quote -> {
            System.out.println("DateTime:" + quote.getLocalDateTime());
            System.out.println("Price:" + quote.getPrice());

        });

        System.out.println("---------------------------------------------------------------------");


    }

    public void updateQuotes(String ticketName) {
        BigDecimal price = quoteProvider.getCurrentPriceByTicketName(ticketName);
        QuoteCreateRequest quoteCreateRequest = new QuoteCreateRequest();
        quoteCreateRequest.setTicketName(ticketName);
        quoteCreateRequest.setLocalDateTime(LocalDateTime.now());
        quoteCreateRequest.setPrice(price);

        quoteService.save(quoteCreateRequest);
    }
}

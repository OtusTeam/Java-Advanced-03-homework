package otus.tabaev.task4.service;

import otus.tabaev.task4.core.QuoteRepository;
import otus.tabaev.task4.core.Quote;
import otus.tabaev.task4.service.dto.QuoteCreateRequest;
import otus.tabaev.task4.service.dto.QuoteInfoResponse;

import java.util.List;
import java.util.stream.Collectors;


public class QuoteService {

    private static QuoteService instance;

    private final QuoteRepository quoteRepository;

    private QuoteService(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    public static synchronized QuoteService getInstance() {
        if (instance == null) {
            instance = new QuoteService(QuoteRepository.getInstance());
        }
        return instance;
    }

    public List<QuoteInfoResponse> getLastNQuotesByTicketName(String ticketName, int count) {
        return quoteRepository.getLastNQuotesByTicketName(ticketName, count)
                .stream()
                .map(this::quoteToQuoteInfoResponse)
                .collect(Collectors.toList());
    }

    public void save(QuoteCreateRequest quoteCreateRequest) {
        quoteRepository.save(this.quoteCreateRequestToQuote(quoteCreateRequest));
    }

    private QuoteInfoResponse quoteToQuoteInfoResponse(Quote quote) {
        QuoteInfoResponse quoteInfoResponse = new QuoteInfoResponse();
        quoteInfoResponse.setPrice(quote.getPrice());
        quoteInfoResponse.setTicketName(quote.getTicketName());
        quoteInfoResponse.setLocalDateTime(quote.getLocalDateTime());
        return quoteInfoResponse;

    }

    private Quote quoteCreateRequestToQuote(QuoteCreateRequest quoteCreateRequest) {
        Quote quote = new Quote();
        quote.setLocalDateTime(quoteCreateRequest.getLocalDateTime());
        quote.setPrice(quoteCreateRequest.getPrice());
        quote.setTicketName(quoteCreateRequest.getTicketName());
        return quote;
    }
}

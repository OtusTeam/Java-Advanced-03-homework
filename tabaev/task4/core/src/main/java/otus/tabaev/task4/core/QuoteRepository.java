package otus.tabaev.task4.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class QuoteRepository {

    private static QuoteRepository instance;

    private Map<String, List<Quote>> quotes;

    private QuoteRepository(ConcurrentHashMap<String, List<Quote>> quotes) {
        this.quotes = quotes;
    }

    public static synchronized QuoteRepository getInstance() {
        if (instance == null) {
            instance = new QuoteRepository(new ConcurrentHashMap<>());
        }
        return instance;
    }

    public List<Quote> getPriceByTicketNameLimitOffset(String ticketName, int offset, int limit) {
        List<Quote> quoteByTicketName = getPriceByTicketName(ticketName);
        if (quoteByTicketName == null) {
            return Collections.emptyList();
        }
        int fromIndex = Math.max(0, offset);
        int toIndex = Math.min(offset + limit, quoteByTicketName.size());
        if (fromIndex >= quoteByTicketName.size()) {
            return Collections.emptyList();
        }
        return quoteByTicketName.subList(fromIndex, toIndex);
    }

    public List<Quote> getLastNQuotesByTicketName(String ticketName, int n) {
        List<Quote> quoteByTicketName = getPriceByTicketName(ticketName);
        if (quoteByTicketName == null || quoteByTicketName.isEmpty()) {
            return Collections.emptyList();
        }
        int fromIndex = Math.max(quoteByTicketName.size() - n, 0);
        return quoteByTicketName.subList(fromIndex, quoteByTicketName.size());
    }

    public void save(Quote quote) {
        quotes.computeIfAbsent(quote.getTicketName(), k -> new ArrayList<>())
                .add(quote);
    }

    private List<Quote> getPriceByTicketName(String ticketName) {
        return quotes.get(ticketName);
    }

}

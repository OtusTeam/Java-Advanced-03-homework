package otus.tabaev.task4.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class QuoteCreateRequest {

    private String ticketName;

    private LocalDateTime localDateTime;

    private BigDecimal price;
}

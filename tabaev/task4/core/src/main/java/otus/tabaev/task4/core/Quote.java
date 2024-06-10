package otus.tabaev.task4.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Quote {

    private String ticketName;

    private LocalDateTime localDateTime;

    private BigDecimal price;

}

package kenny.project.CurrencyConvertor.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CurrencyRequestDTO {
    private String fromCurrency;
    private String toCurrency;
    private BigDecimal originalAmount;
    private String inputPath;
}

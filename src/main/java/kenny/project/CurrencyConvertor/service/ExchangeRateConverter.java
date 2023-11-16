package kenny.project.CurrencyConvertor.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

@Service
public class ExchangeRateConverter {
    public BigDecimal convert(Map<String, BigDecimal> exchangeRates,
                              String fromCurrency,
                              String toCurrency) {
        BigDecimal fromRate = exchangeRates.get(fromCurrency);
        BigDecimal toRate = exchangeRates.get(toCurrency);

        if ("EUR".equals(fromCurrency)) {
            return toRate;
        }

        if ("EUR".equals(toCurrency)) {
            return BigDecimal.ONE.divide(fromRate, 6, RoundingMode.HALF_UP);
        }


        BigDecimal effectiveRate = BigDecimal.ONE.divide(fromRate,6, RoundingMode.HALF_UP)
                .multiply(toRate);

        return effectiveRate.setScale(6, RoundingMode.HALF_UP);
    }
}


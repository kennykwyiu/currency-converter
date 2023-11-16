package kenny.project.CurrencyConvertor.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class PrincipalCalculator {
    public BigDecimal compute(BigDecimal originalAmount,
                              BigDecimal effectiveExchangeRate) {
        return originalAmount.multiply(effectiveExchangeRate)
                .setScale(2, RoundingMode.HALF_UP);
    }
}

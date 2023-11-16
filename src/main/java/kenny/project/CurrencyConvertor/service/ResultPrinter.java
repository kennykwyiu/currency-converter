package kenny.project.CurrencyConvertor.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ResultPrinter {
    public void print(String fromCurrency,
                      String toCurrency,
                      BigDecimal originalAmount,
                      BigDecimal principal) {
        System.out.printf("From %s | To: %s | Amount: %s | Result: %s %s\n",
                fromCurrency,
                toCurrency,
                originalAmount,
                toCurrency,
                principal
        );
    }
}


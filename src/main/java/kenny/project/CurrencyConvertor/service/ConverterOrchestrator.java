package kenny.project.CurrencyConvertor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

@Service
public class ConverterOrchestrator {
    @Autowired
    private ExchangeRateConverter convertor;
    @Autowired
    private ExchangeRateExtractor extractor;
    @Autowired
    private PrincipalCalculator calculator;

    public BigDecimal process(String fromCurrency,
                              String toCurrency,
                              BigDecimal originalAmount) throws IOException, ParserConfigurationException, SAXException {
        Map<String, BigDecimal> rates = extractor.extract();
        BigDecimal effectiveExchangeRate = convertor.convert(rates,
                fromCurrency,
                toCurrency);
        return calculator.compute(originalAmount, effectiveExchangeRate);
    }
}

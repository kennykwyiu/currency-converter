package kenny.project.CurrencyConvertor.controller;

import kenny.project.CurrencyConvertor.dto.CurrencyFactory;
import kenny.project.CurrencyConvertor.dto.CurrencyRequestDTO;
import kenny.project.CurrencyConvertor.dto.CurrencyResponseDTO;
import kenny.project.CurrencyConvertor.service.ConverterOrchestrator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.math.BigDecimal;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/currency")
public class CurrencyController {

    @Autowired
    private ConverterOrchestrator converterOrchestrator;

    @Autowired
    private CurrencyFactory currencyFactory;

    @PostMapping
    public ResponseEntity<CurrencyResponseDTO> process(@RequestBody CurrencyRequestDTO requestDTO) throws IOException
            , ParserConfigurationException, SAXException {
        String fromCurrency = requestDTO.getFromCurrency();
        String toCurrency = requestDTO.getToCurrency();
        String inputPath = requestDTO.getInputPath();
        BigDecimal originalAmount = requestDTO.getOriginalAmount();

        BigDecimal result = converterOrchestrator.process(fromCurrency,
                toCurrency,
                originalAmount);
        CurrencyResponseDTO responseDTO = currencyFactory.toResponseDTO(requestDTO, result);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseDTO);

    }
}

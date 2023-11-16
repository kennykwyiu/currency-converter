package kenny.project.CurrencyConvertor.dto;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CurrencyFactory {

    public CurrencyResponseDTO toResponseDTO(CurrencyRequestDTO requestDTO,
                                             BigDecimal result) {
        return CurrencyResponseDTO.builder()
                .fromCurrency(requestDTO.getFromCurrency())
                .toCurrency(requestDTO.getToCurrency())
                .originalAmount(requestDTO.getOriginalAmount())
                .result(result)
                .build();
    }

}

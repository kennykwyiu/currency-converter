package kenny.project.CurrencyConvertor.repository;

import kenny.project.CurrencyConvertor.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {
}

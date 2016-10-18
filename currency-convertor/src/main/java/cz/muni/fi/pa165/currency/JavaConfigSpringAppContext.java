package cz.muni.fi.pa165.currency;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.Currency;

/**
 * Created by MONNY on 18-Oct-16.
 */
@Configuration
@ComponentScan
public class JavaConfigSpringAppContext {

    @Inject
    private ExchangeRateTable exchangeRateTable;


    @Bean
    public CurrencyConvertor newCurrencyConvertor() {
        return new CurrencyConvertorImpl(exchangeRateTable);
    }

}

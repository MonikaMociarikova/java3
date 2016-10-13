package cz.muni.fi.pa165.currency;

import java.math.BigDecimal;
import java.util.Currency;
import javax.inject.Named;

/**
 * Created by xmociar on 6.10.16.
 */
@Named
public class ExchangeRateTableImpl implements ExchangeRateTable {
    @Override
    public BigDecimal getExchangeRate(Currency sourceCurrency, Currency targetCurrency) throws ExternalServiceFailureException {
        if (sourceCurrency == Currency.getInstance("EUR") && targetCurrency == Currency.getInstance("CZK")) {
            return BigDecimal.valueOf(27);
        }
        return null;
    }
}

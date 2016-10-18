package cz.muni.fi.pa165.currency;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * Created by MONNY on 18-Oct-16.
 */
public class MainJavaConfig {

    public static void main(String[] args) {

        ApplicationContext applicationContext
                = new AnnotationConfigApplicationContext(JavaConfigSpringAppContext.class);

        CurrencyConvertor currencyConvertor =
                applicationContext.getBean("newCurrencyConvertor",CurrencyConvertor.class);


        currencyConvertor.convert(Currency.getInstance("EUR"),Currency.getInstance("CZK"), BigDecimal.valueOf(1));

    }
}

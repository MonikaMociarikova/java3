package cz.muni.fi.pa165.currency;
import java.math.BigDecimal;
import java.util.Currency;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by xmociar on 6.10.16.
 */
public class MainXML {

    /*public static void main(String[] args) {

        
        ApplicationContext applicationContext 
                = new ClassPathXmlApplicationContext("/WEB-INF/springContext.xml");
        
        CurrencyConvertor currencyConvertor = 
                applicationContext.getBean(CurrencyConvertorImpl.class);
        
        
        currencyConvertor.convert(Currency.getInstance("EUR"),Currency.getInstance("CZK"),BigDecimal.valueOf(1));
    }*/
}
